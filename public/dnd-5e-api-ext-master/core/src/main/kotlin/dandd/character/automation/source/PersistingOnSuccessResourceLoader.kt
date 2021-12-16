package dandd.character.automation.source

import arrow.core.Either
import dandd.character.automation.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal data class PersistingOnSuccessResourceLoader<T, ID: Any>(val loader: ResourceLoader<T, ID>,
                                                         val persister: ResourcePersister<T, ID>,
                                                         val getId: suspend (T) -> Result<ID>): ResourceLoader<T, ID> {
    override suspend fun loadResource(id: ID): Result<T> {
        val loadResult = loader.loadResource(id)

        return when(loadResult) {
            is Either.Left<Throwable> -> loadResult
            is Either.Right -> persister.persistResource(id, loadResult.b)
        }
    }

    override suspend fun loadAll(): List<Result<T>> =
        loadAll(emptySet())

    override suspend fun loadAll(excludeIds: Set<ID>): List<Result<T>> =
        coroutineScope {
            loader.loadAll(excludeIds).map { either ->
                async {
                    when(either) {
                        is Either.Left -> either
                        is Either.Right -> getId(either.b).suspendFlatMap { name ->
                            persister.persistResource(name, either.b)
                        }
                    }
                }
            }.awaitAll()
        }
}