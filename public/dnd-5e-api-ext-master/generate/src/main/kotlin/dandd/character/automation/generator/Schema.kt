package dandd.character.automation.generator

import java.lang.Double
import java.lang.IllegalStateException
import java.lang.RuntimeException

sealed class Schema {
    abstract fun printFmt(indent: Int): String
    abstract fun isInstanceOf(other: Schema): Boolean
}
data class ConcreteSchema(val clazz: Class<*>): Schema() {
    override fun printFmt(indent: Int): String {
        val indentStr = " ".repeat(indent)
        return "${indentStr}${pkg()}.${name()}"
    }
    fun pkg() = clazz.packageName
    fun name() = clazz.simpleName

    override fun isInstanceOf(other: Schema): Boolean = when(other) {
        is ConcreteSchema -> this == other
        else -> false
    }
}

data class ListSchema(val schema: Schema?): Schema() {

    override fun isInstanceOf(other: Schema): Boolean = when(other) {
        is ListSchema -> schema?.let { innerSchema -> other.schema?.let {otherSchema -> innerSchema.isInstanceOf(otherSchema) }}?:true
        else -> false
    }

    fun merge(other: Schema): ListSchema {
        if(schema == null) return ListSchema(other)

        if(schema is ConcreteSchema && other is ConcreteSchema) {
            if(schema == other) {
                return this
            } else {
                throw RuntimeException("Expected ${schema.printFmt(0)} to be ${other.printFmt(0)}")
            }
        }

        if(schema is ListSchema && other is ListSchema) {
            return ListSchema(other.schema?.let(schema::merge)
                    ?: this)
        }
        if(schema is ObjectSchema && other is ObjectSchema) {
            return ListSchema(schema.computeOptionalFields(other))
        }

        throw RuntimeException("COuld not merge ${schema.printFmt(0)} with ${other.printFmt(0)}")
    }

    override fun printFmt(indent: Int): String {
        val indentStr = " ".repeat(indent)

        return "${indentStr}List<[\n"+
                schema?.printFmt(indent + 1) +
                "\n${indentStr}]>"
    }
}
data class ObjectSchema(val fields: Map<String, Schema>): Schema() {
    override fun printFmt(indent: Int): String {
        val indentStr = " ".repeat(indent)
        return "${indentStr}Map<\n" +
                fields.map { (key, value) -> "${value.printFmt(indent + 1)} - $key" }.joinToString("\n") +
                "\n${indentStr}>"
    }

    override fun isInstanceOf(other: Schema): Boolean = when(other) {
        is ObjectSchema -> other.fields
                .filter  { (otherKey, otherSchema) ->
                      this.fields.containsKey(otherKey)  ||
                              otherSchema !is OptionalSchema
                  }
                  .all { (key, otherScema) -> this.fields.get(key)?.isInstanceOf(otherScema)?:false}
        else -> false
    }



    private val upgrades: Map<Schema, Schema> = mapOf(ConcreteSchema(Integer::class.java) to ConcreteSchema(Double::class.java))
    fun upgrade(one: Schema, other: Schema): Schema? {
        if(upgrades.get(other) == one) return one
        if(upgrades.get(one) == other) return other
        else return null
    }
    fun computeOptionalFields(other: ObjectSchema): ObjectSchema {
        val distinctKeys= fields.keys + other.fields.keys

        val schemas = distinctKeys.mapNotNull { key ->
            val thisSchema = fields.get(key)
            val thatSchema = other.fields.get(key)
            if (thisSchema == null && thatSchema == null) {
                null
            } else if (thisSchema != null && thatSchema == null) {
                key to requireOptional(thisSchema)
            } else if (thisSchema == null && thatSchema != null) {
                key to requireOptional(thatSchema)
            } else { // if(thisSchema!= null && thatSchema != null)
                val unwrappedThisSchema = unwrapOptional(thisSchema!!)
                val unwrappedThatSchema = unwrapOptional(thatSchema!!)

                if(unwrappedThisSchema is ListSchema && unwrappedThatSchema is ListSchema) {
                    val listMerged = unwrappedThatSchema.schema?.let(unwrappedThisSchema::merge)?:unwrappedThisSchema
                    key to
                            if(thisSchema is OptionalSchema || thatSchema is OptionalSchema) OptionalSchema(listMerged)
                            else listMerged
                } else if(unwrappedThisSchema is ObjectSchema && unwrappedThatSchema is ObjectSchema) {
                    val objectMerged = unwrappedThatSchema.computeOptionalFields(unwrappedThatSchema)
                    key to
                            if(thisSchema is OptionalSchema || thatSchema is OptionalSchema) OptionalSchema(objectMerged)
                            else objectMerged
                } else if(unwrappedThisSchema == unwrappedThatSchema) {
                    key to preserveOptional(thisSchema, thatSchema)
                } else {
                    upgrade(unwrappedThatSchema, unwrappedThisSchema)?.let { upgraded ->
                        key to
                                if(thisSchema is OptionalSchema || thatSchema is OptionalSchema) OptionalSchema(upgraded)
                                else upgraded
                    }?: throw IllegalStateException("ERROR; could not merge \n\t${thisSchema.printFmt(2)}\nand\n\t${thatSchema.printFmt(2)}\nexpected them to of a similar type but they were too different")
                }
            }
        }

        return ObjectSchema(schemas.toMap())
    }

    fun preserveOptional(thisSchema: Schema, thatSchema: Schema): Schema =
            when(thisSchema) {
                is OptionalSchema -> thisSchema
                else -> thatSchema
            }

    fun unwrapOptional(schema: Schema): Schema =
            when(schema) {
                is OptionalSchema -> schema.schema
                else -> schema
            }

    fun requireOptional(schema: Schema): OptionalSchema =
            when(schema) {
                is OptionalSchema -> schema
                else -> OptionalSchema(schema)
            }
}
data class OptionalSchema(val schema: Schema): Schema() {
    override fun isInstanceOf(other: Schema): Boolean = when(other) {
        is OptionalSchema -> schema.isInstanceOf(other.schema)
        else -> schema.isInstanceOf(other)
    }
    override fun printFmt(indent: Int): String {
        val indentStr = " ".repeat(indent)

        return "${indentStr}Optional<\n${schema.printFmt(indent + 1)}\n>"
    }
}
