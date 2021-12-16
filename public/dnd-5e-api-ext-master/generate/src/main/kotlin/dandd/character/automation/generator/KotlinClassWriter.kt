package dandd.character.automation.generator

import java.io.File


class KotlinClassWriter(val mainClassName: String,
                        val pkg: String,
                        val dictionary: Map<String, ObjectSchema>,
                        val clientWriterConfig: ClientWriterConfig) {
    private val transformTypes = mapOf(ConcreteSchema(java.lang.Integer::class.java) to "Int")

    fun writeAll(directory: String) {
        val targetDirectory = "$directory/${pkg.split(".").joinToString("/")}"
        File(targetDirectory).mkdirs()

        dictionary.forEach { (name, schema) ->
            File("$targetDirectory/$name.kt").writeText(schema.write(name))
        }
    }

    private fun ObjectSchema.write(name: String): String {
        val ignoreImports = setOf("Int", "String", "Map", "List", "Long", "Integer", "Boolean", "Double")
        val imports = fields
                .filter {(name, schema) ->
                    when(schema) {
                        is ConcreteSchema -> !ignoreImports.contains(schema.name())
                        else -> false
                    }
                }
                .mapNotNull { (name, schema) -> when(schema) {
                    is ConcreteSchema -> "import ${schema.pkg()}.${schema.name()}"
                    else -> null
                } } + listOf(
                        "import io.ktor.client.HttpClient",
                        "import web.api.ApiClient",
                        "import web.api.ClientParseError",
                        "import web.core.Either",
                        "import web.core.bindRight",
                        "import web.core.mapLeft",
                        "import web.parse.*",
                        "import kotlin.js.Json"
        )

        val classDeclaration = "data class $name("
        val indent = "    "
        return """
package $pkg

${imports.joinToString("\n")}

$classDeclaration
$indent${fields.map { (name, schema) -> "val ${escapeFieldName(name)}: ${schema.writeType(name)}" }.joinToString(",\n$indent")}
) {
    companion object {
        val resourceTypeName = "${clientWriterConfig.fieldNames.joinToString("-")}"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, $name> =
            json.parse {
                obj {
                    $name(
${writeParserFields().joinToString(",\n")}
                    )
                }
            }
        ${clientMethod(name)}
    }
}
"""
    }

    fun clientMethod(name: String): String {
        if (name == mainClassName) {
            return clientWriterConfig.materialize(mainClassName).clientCLass
        } else {
            return  ""
        }
    }
    private fun ObjectSchema.writeParserFields(): List<String> {
        val writer = KotlinJsonParserWriter(dictionary, 6)
        return fields.map { (fieldName, value) ->
            writer.generateParser(value, fieldName)
        }
    }

    private fun Schema.writeType(name: String? = null): String =
            when(this) {
                is ConcreteSchema -> transformTypes[this]?:this.name()
                is ListSchema -> "List<${this.schema?.writeType(name)?:"Any"}>"
                is ObjectSchema -> dictionary.findType(this, name)?.key ?: "Map<String, Any>"
                is OptionalSchema -> "${this.schema.writeType(name)}?"
            }

    private val replacementTypeName = mapOf(
            ConcreteSchema(Integer::class.java) to "Int"
    )
    private fun replaceSchema(concreteSchema: ConcreteSchema) {
        replacementTypeName.get(concreteSchema)?:concreteSchema.name()
    }
    private val keywords = setOf("if", "else", "class", "fun", "private", "internal", "public", "sealed", "when",
            "this", "protected", "for", "while", "return", "as", "break", "continue", "do", "false", "true", "in",
            "is", "null", "object", "super", "throw", "try", "typealias", "typeof", "val", "var")

    private fun escapeFieldName(name: String): String =
            if(keywords.contains(name)) {
                "`$name`"
            } else if(name.matches("[0-9]+.*".toRegex())) {
                "_$name"
            } else {
                "$name"
            }


}

fun Map<String, ObjectSchema>.findType(schema: ObjectSchema, name: String?): Map.Entry<String, ObjectSchema>? {
    return entries.find { schema == it.value }
            ?: name?.let { entries.find {
                keyToClassName(name) == it.key
            } }
            ?: entries.find { schema.isInstanceOf(it.value) }

}

