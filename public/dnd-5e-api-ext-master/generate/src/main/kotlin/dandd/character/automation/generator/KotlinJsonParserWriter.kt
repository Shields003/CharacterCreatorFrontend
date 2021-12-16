package dandd.character.automation.generator

import arrow.core.extensions.list.foldable.foldLeft
import java.lang.RuntimeException

data class ClientWriterConfig(val fieldNames: List<String>,
                              val urlPattern: String) {
    fun materialize(resourceClassName: String) =
        ClientMethodWriter(resourceClassName, fieldNames, urlPattern)
}

//fun clientWriterConfig(resourceType: String): ClientWriterConfig {
//    return ClientWriterConfig(listOf("index"), "/api/$resourceType/{}")
//}

fun clientWriterConfig(resourceTypes: List<String>): ClientWriterConfig {
    return ClientWriterConfig(resourceTypes, "/api/${resourceTypes.map{ "$it/{}" }.joinToString("/")}")
}

data class ClientMethodWriter(
        private val resourceClassName: String,
        private val fieldNames: List<String>,
        private val urlPattern: String) {

    private val sanitizedFields = fieldNames.map { it
            .split("[^a-zA-Z]+".toRegex())
            .map { it.capitalize() }
            .joinToString("")
            .decapitalize()
    }
    private val methodParams: String = sanitizedFields
            .map { "$it: String" }
            .joinToString(", ")

    private val url = sanitizedFields
            .foldLeft(urlPattern) { prev, next ->
                prev.replaceFirst("{}", "\${$next}")
            }

    val clientCLass = """
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<$resourceClassName> {
        override val parse = parseResponseBody
        
        suspend fun get$resourceClassName($methodParams) = 
            getResourceByUri("$url")
    }
"""
}
data class KotlinJsonParserWriter(
        val dictionary: Map<String, ObjectSchema>,
        val indentLevel: Int,
        val indentStr: String = "    ") {


    fun generateParser(schema: Schema, fieldName: String? = null, skipFieldNameForNullable: Boolean = false): String {
        return when(schema) {
            is ListSchema -> writeParse(schema, fieldName, skipFieldNameForNullable)
            is ObjectSchema -> writeParse(schema, fieldName, skipFieldNameForNullable)
            is OptionalSchema -> writeParse(schema, fieldName)
            is ConcreteSchema -> writeParse(schema, fieldName, skipFieldNameForNullable)
        }
    }

    private val currentIndentation = indentStr.repeat(indentLevel)

    private fun closureCall(methodName: String, fieldName: String? = null, printChild: () -> String): String {
        val fieldNamePrefix = fieldName?.let { "\"$it\"." }?:""
        return "${currentIndentation}${fieldNamePrefix}${methodName} {\n" +
                printChild() +
                "\n${currentIndentation}}"
    }

    private fun valueCall(methodName: String, fieldName: String? = null): String {
        val fieldNamePrefix = fieldName?.let { "\"$it\"." }?:""
        return "${currentIndentation}${fieldNamePrefix}${methodName}()"
    }

    private fun child() =
            copy(indentLevel = indentLevel + 1)



    private fun writeParse(schema: ListSchema, fieldName: String? = null, skipFieldName: Boolean = false): String {
        val  writtenFieldName = if(skipFieldName) null else fieldName
        return closureCall("arr", writtenFieldName) {
            schema.schema?.let {
                child().generateParser(it, fieldName, true)
            }?:""
        }
    }
    private fun writeParse(schema: ObjectSchema, fieldName: String? = null, skipFieldName: Boolean = false): String {
        val  writtenFieldName = if(skipFieldName) null else fieldName

        return closureCall("obj", writtenFieldName) {
            dictionary.findType(schema, fieldName)
                    ?.let { (k, v) -> writeDictionaryParse(k, fieldName) }
                    ?: schema.fields
                            .map { (childFieldName, childSchema) ->
                                child().generateParser(childSchema, childFieldName)
                            }
                            .joinToString("\n")
        }
    }

    private fun writeDictionaryParse(className: String, fieldName: String?): String = "$currentIndentation$indentStr$className.from(node).bind()"


    private fun writeParse(schema: OptionalSchema, fieldName: String? = null): String {
        return closureCall("nullable", fieldName) {
            child().generateParser(schema.schema, fieldName, true)
        }
    }

    private fun writeParse(schema: ConcreteSchema, fieldName: String? = null, skipFieldName: Boolean = false): String {
        val  writtenFieldName = if(skipFieldName) null else fieldName

        if(schema.name() == "Int" || schema.name() == "Integer") {
            return valueCall("int", writtenFieldName)
        }
        if(schema.name() == "Long") {
            return valueCall("long", writtenFieldName)
        }
        if(schema.name() == "Double") {
            return valueCall("double", writtenFieldName)
        }
        if(schema.name() == "Boolean") {
            return valueCall("boolean", writtenFieldName)
        }
        if(schema.name() == "String") {
            return valueCall("str", writtenFieldName)
        }
        throw RuntimeException("unhandled type: $fieldName, $schema")
    }
}