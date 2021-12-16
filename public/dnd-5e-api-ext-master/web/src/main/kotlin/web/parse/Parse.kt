package web.parse

import web.core.*
import kotlin.js.Json


interface Parser<T>: RightBinding<ParseError> {
    val node: T

    fun <S> readNullableField(name: String, fn:  Parser<Any>.() -> S): S?
    fun <S> String.nullable(fn: Parser<Any>.() -> S): S? = readNullableField(this, fn)

    fun readInt(): Int
    fun readIntField(name: String): Int

    fun int(): Int = readInt()
    fun String.int(): Int = readIntField(this)

    fun readLong(): Long
    fun readLongField(name: String): Long

    fun long(): Long = readLong()
    fun String.long(): Long = readLongField(this)

    fun readDouble(): Double
    fun readDoubleField(name: String): Double

    fun double(): Double = readDouble()
    fun String.double(): Double = readDoubleField(this)

    fun readBoolean(): Boolean
    fun readBooleanField(name: String): Boolean

    fun boolean(): Boolean = readBoolean()
    fun String.boolean(): Boolean = readBooleanField(this)
    
    fun readString(): String
    fun readStringField(name: String): String

    fun str(): String = readString()
    fun String.str(): String = readStringField(this)

    fun <T> readObject(body: Parser<Json?>.() -> T): T
    fun <T> readObjectField(name: String, body: Parser<Json?>.() -> T): T

    fun <T> obj(body: Parser<Json?>.() -> T): T = readObject(body)
    fun <T> String.obj(body: Parser<Json?>.() -> T): T = readObjectField(this, body)

    fun <T> readArray(body: Parser<Any?>.() -> T): List<T>
    fun <T> readArrayField(name: String, body: Parser<Any?>.() -> T): List<T>

    fun <T> arr(body: Parser<Any?>.() -> T): List<T> = readArray(body)
    fun <T> String.arr(body: Parser<Any?>.() -> T): List<T> = readArrayField(this, body)

}


fun <I, R> I.parse(comprehension: Parser<I>.() -> R): Either<ParseError, R> =
        Comprehension.comprehend {
            ParserImpl(this, this@parse).comprehension()
        }

private data class ParserImpl<T>(val binding: RightBinding<ParseError>, override val node: T): RightBinding<ParseError> by binding, Parser<T> {

    override fun <S> readNullableField(name: String, fn: Parser<Any>.() -> S): S? =
        field(name) { node?.let { it.parse(fn).bind() } }.bind()

    override fun readInt(): Int = parseInt().bind()
    override fun readIntField(name: String): Int = field(name) { readInt() }.bind()

    override fun readLong(): Long = parseLong().bind()
    override fun readLongField(name: String): Long = field(name) { readLong() }.bind()

    override fun readDouble(): Double = parseDouble().bind()
    override fun readDoubleField(name: String): Double = field(name) { readDouble() }.bind()

    override fun readBoolean(): Boolean = parseBoolean().bind()
    override fun readBooleanField(name: String): Boolean = field(name) { readBoolean() }.bind()

    override fun readString() = parseString().bind()
    override fun readStringField(name: String) = field(name) { readString() }.bind()

    override fun <T> readObject(body: Parser<Json?>.() -> T) =
        expectObject { body() }.bind()

    override fun <T> readObjectField(name: String, body: Parser<Json?>.() -> T) =
        field(name) { readObject(body) }.bind()

    override fun <T> readArray(body: Parser<Any?>.() -> T) =
         expectArray { body() }.bind()

    override fun <T> readArrayField(name: String, body: Parser<Any?>.() -> T) =
        field(name) { readArray(body) }.bind()

    private fun parseInt(): Either<ParseError, Int> = when(node) {
        is Int -> Right(node)
        else -> Left(ExpectedInt(JSON.stringify(node)))
    }

    private fun parseLong(): Either<ParseError, Long> = when(node) {
        is Long -> Right(node)
        else -> Left(ExpectedLong(JSON.stringify(node)))
    }

    private fun parseDouble(): Either<ParseError, Double> = when(node) {
        is Double -> Right(node)
        else -> Left(ExpectedDouble(JSON.stringify(node)))
    }

    private fun parseBoolean(): Either<ParseError, Boolean> = when(node) {
        is Boolean -> Right(node)
        else -> Left(ExpectedBoolean(JSON.stringify(node)))
    }

    private fun parseString(): Either<ParseError, String> = when(node) {
        is String -> Right(node)
        else -> Left(ExpectedString(JSON.stringify(node)))
    }

    private fun <T> expectObject(parseObject: Parser<Json?>.() -> T): Either<ParseError, T> = Either
            .catching { node as Json? }
            .mapLeft { CastError(it) }
            .bindRight { it.parse(parseObject) }
            .mapLeft { ExpectedObject(JSON.stringify(node), it) }

    private fun <Field> field(name: String, parseField: Parser<Any?>.() -> Field): Either<ParseError, Field> =
        expectObject {
            val fieldValue = node?.let { it[name] }
            fieldValue.parse(parseField)
                    .mapLeft { ExpectedField(name, it) }
                    .bind()
        }

    private fun <Child> expectArray(parseChild: Parser<Any?>.() -> Child): Either<ParseError, List<Child>> {
        val parseResult =  when(node) {
            is Array<*> -> node
                    .map { it.parse(parseChild) }
                    .liftRight()
                    .mapLeft { Aggregate(it) }
            else -> node
                    .parse(parseChild)
                    .mapRight { listOf(it) }
        }

        return parseResult.mapLeft { ExpectedArray(JSON.stringify(node), it) }
    }
}


private object Comprehension: ComprehensionContext<ParseError> {
    override fun BindError<ParseError>.unboxError(): ParseError =
            when(this) {
                is Expected -> error
                is Unexpected -> UnexpectedBindError(error)
            }
}
