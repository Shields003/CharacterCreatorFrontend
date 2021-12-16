package dandd.character.automation.generator

data class ModelTree(val name: String, val pkg: String, val keyValues: Map<*, *>, val schemaRegistrar: ModelRegistrar) {
    val schema = keyValues.schema(name)

    private fun Map<*, *>.schema(name: String? = null): ObjectSchema {
         val thisSchema = ObjectSchema(this.mapNotNull { entry ->
             entry.key?.let { key ->
                 entry.value?.let { value ->
                     when (key) {
                         is String ->
                             key to when (value) {
                                 is Map<*, *> -> value.schema(key)
                                 is List<*> -> value.schema(key)
                                 else -> ConcreteSchema(value.javaClass)
                             }
                         else -> null
                     }
                 }
             }
         }.toMap())
        schemaRegistrar.register(name, thisSchema)
        return thisSchema
    }

    private fun List<*>.schema(key: String): ListSchema = this
            .mapNotNull {
                when(it) {
                    is Map<*, *> -> ListSchema(it.schema(key))
                    is List<*> -> ListSchema(it.schema(key))
                    else -> ListSchema(it?.let { ConcreteSchema(it.javaClass) })
                }
            }
            .fold(ListSchema(null)) { prev, next -> next.schema?.let(prev::merge)?:prev }
}