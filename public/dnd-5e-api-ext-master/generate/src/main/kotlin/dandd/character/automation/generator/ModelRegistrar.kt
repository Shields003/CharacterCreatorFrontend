package dandd.character.automation.generator

import dandd.character.automation.source.ResourceConfig


class ModelRegistrar(val pkg: String,
val resourceConfigs: List<ResourceConfig<*, *>>) {
    private val unnamedSchemas = mutableSetOf<ObjectSchema>()

    private val namedSchemas = mutableMapOf<String, ObjectSchema>()

    fun named() = namedSchemas.toMap()
    fun unnamed() = unnamedSchemas.toSet().filter { unnamed ->
        !namedSchemas.any { named -> unnamed.isInstanceOf(named.value)}
    }

    fun register(name: String?, objectSchema: ObjectSchema) {
        name?.let {
            namedSchemas.get(it)
                    ?.let { namedSchemas.put(name, objectSchema.computeOptionalFields(it)) }
                    ?: namedSchemas.put(name, objectSchema)
            unnamedSchemas.remove(objectSchema)
        } ?: unnamedSchemas.add(objectSchema)
    }

    fun exportDictionary(): Map<String, ObjectSchema> {
        val allUnnamed = unnamed()
                .map { it.fields.computNameFromKeys() to it }
                .toList()

        val allNamed = named()
                .mapKeys { (key, _) ->
                    val key = key.split("_").map { it.capitalize() }.joinToString("")
                    if (key.matches("[0-9]+.*".toRegex())) {
                        "_$key"
                    } else {
                        key
                    }
                }
                .toMap()

        val explicitSchemas = resourceConfigs.mapNotNull {
            allNamed.get(it.className)?.let { schema -> it.className to schema }
        }.toMap()

        val allRegistered = allUnnamed + allNamed.toList()

        val inferredSchemas =  allRegistered
                .filter { !explicitSchemas.containsKey(it.first) }
                .groupBy { it.second }.mapNotNull { (schema, entries) ->
                    when(entries) {
                        emptyList<Pair<String, ObjectSchema>>() -> null
                        entries.take(1) -> entries.first()
                        else -> schema.fields.computNameFromKeys() to schema
                    }
                }
                .toMap()

        return explicitSchemas + inferredSchemas
    }
}

fun keyToClassName(key: String) = key.split("_").map { it.capitalize() }.joinToString("")
private fun Map<String, Any>.computNameFromKeys(): String = keys.flatMap { it.split("_") }.map { it.capitalize() }.joinToString("")
