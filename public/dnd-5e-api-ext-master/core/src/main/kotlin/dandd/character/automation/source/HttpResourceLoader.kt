package dandd.character.automation.source

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import arrow.core.extensions.either.monad.flatten
import arrow.core.flatMap
import dandd.character.automation.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.json.JSONObject
import java.lang.RuntimeException
suspend fun <A, B, C> Either<A, B>.suspendFlatMap(f: suspend (B) -> Either<A, C>) =
        when(this) {
            is Either.Left -> Either.left(this.a)
            is Either.Right -> f(this.b)

        }
suspend fun <A, B, C> Either<A, B?>.suspendFlatMapNotNull(f: suspend (B) -> Either<A, C>) =
        when(this) {
            is Either.Left -> Either.left(this.a)
            is Either.Right -> {
                val result = b
                when (result) {
                    null -> Either.Right(null)
                    else -> f(result)
                }
            }
        }
fun <A, B> Either<A, List<Either<A, B>>>.lift() =
    when(this) {
        is Either.Left -> listOf(Either.left(this.a))
        is Either.Right -> this.b
    }

fun <A, B> List<Either<A, B?>>.filterRightNotNull(): List<Either<A, B>> =
        mapNotNull { when(it) {
            is Either.Right -> it.b?.let { Either.right(it) }
            is Either.Left -> it
        } }

fun <T> simpleHttpResourceLoader(urlBase: String,
                                 resourceType: String,
                                 readingMapper: suspend (String) -> Result<T>): ResourceLoader<T, String> {
    return HttpResourceLoader(
            createRootUrl = { "$urlBase/api/$resourceType" },
            createSubUrl = { id: String -> "$urlBase/api/$resourceType/${id.replace("\\s+".toRegex(), "+")}" },
            readingMapper = readingMapper,
            childIds = { resultEntry: Any ->
                listOf(Either.catch {
                    when (resultEntry) {
                        is JSONObject -> resultEntry.get("index").toString()
                        else -> throw RuntimeException("$resultEntry was not a json object")
                    }
                })
            }

    )
}

internal fun <T> nestedHttpResourceLoader(urlBase: String,
                                          resourceType: String,
                                          subResourceType: String,
                                          readingMapper: suspend (String) -> Result<T>): HttpResourceLoader<T, Pair<String, String>> {
    return HttpResourceLoader(
            createRootUrl = { "$urlBase/api/$resourceType" },
            createSubUrl = { id: Pair<String, String> -> "$urlBase/api/$resourceType/${id.first.replace("\\s+".toRegex(), "+")}/$subResourceType/${id.second.replace("\\s+".toRegex(), "+")}" },
            readingMapper = readingMapper,
            childIds = { resultEntry: Any -> Either
                    .catch {
                        when (resultEntry) {
                            is JSONObject -> resultEntry.get("index").toString()
                            else -> throw RuntimeException("$resultEntry was not a json object")
                        }
                    }
                    .suspendFlatMap { id1 ->
                        Either.catch {
                            khttp.get("$urlBase/api/$resourceType/$id1/levels").jsonArray
                                    .mapIndexed { idx, _ -> Right(id1 to idx.toString()) }
                        }
                    }
                    .lift()
            })
}

internal data class HttpResourceLoader<T, ID: Any>(
        val createRootUrl: () -> String,
        val createSubUrl: (ID) -> String,
        val readingMapper: suspend (String) -> Result<T>,
        val childIds: suspend (Any) -> List<Result<ID>>): ResourceLoader<T, ID> {//: ResourceLoader<T> {
    override suspend fun loadAll(excludeIds: Set<ID>): List<Result<T>> =
            Either
                    .catch {
                        khttp.get(createRootUrl())
                                .jsonObject
                                .getJSONArray("results")
                                .toList()
                    }
                    .suspendFlatMap { queryResults -> Either.catch { coroutineScope {
                        queryResults
                                .map { async { childIds(it) } }
                                .awaitAll()
                                .flatten()
                                .map { idResult -> async {
                                    idResult.suspendFlatMap { id ->
                                        fetchOrSkip(id, excludeIds)
                                    }
                                } }
                                .awaitAll()
                                .filterRightNotNull()
                    } } }
                    .lift()


    private suspend fun fetchOrSkip(id: ID, excludeIds: Set<ID>): Result<T?> =
                if(excludeIds.contains(id)) {
                    Either.right(null)
                } else {
                    loadResource(id)
                }

    override suspend fun loadAll(): List<Result<T>> {
        return loadAll(emptySet())
    }

    override suspend fun loadResource(id: ID): Result<T> = Either.catch {
        khttp.get(createSubUrl(id))
    }.flatMap { response ->
        if(response.statusCode / 100 != 2) {
            Left(HttpRequestFailed(response.statusCode, String(response.content)))
        } else {
            Right(String(response.content))
        }
    }.suspendFlatMap(readingMapper)

}

data class HttpRequestFailed(val statusCode: Int, val content: String): RuntimeException("http request failed: $statusCode: $content")