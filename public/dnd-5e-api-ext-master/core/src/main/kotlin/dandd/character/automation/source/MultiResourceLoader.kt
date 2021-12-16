package dandd.character.automation.source

import arrow.core.Either
import dandd.character.automation.Result
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.fold

internal data class MultiResourceLoader<T, ID: Any>(val loaders: List<ResourceLoader<T, ID>>,
                                               val getId: suspend (T) -> Result<ID>): ResourceLoader<T, ID> {
    companion object {
        object NoLoaders: Throwable("No Loaders")
        private fun <T> initialResult(): Either<Throwable, T> =
                Either.left(NoLoaders)

    }

    override suspend fun loadAll(): List<Result<T>> = loadAll(emptySet())

    override suspend fun loadResource(id: ID): Result<T> {
        return loaders.asFlow().fold(initialResult()) { ongoing, loader ->
            when (ongoing) {
                is Either.Left<Throwable> -> loader.loadResource(id)
                is Either.Right<T> -> ongoing
            }
        }
    }

    override suspend fun loadAll(excludeIds: Set<ID>): List<Result<T>> =
            loaders
                    .asFlow().fold(emptyList<Result<Pair<ID, T>>>()) { ongoing, loader ->
                        val excludes = ongoing.mapNotNull { when(it) {
                            is Either.Right -> it.b.first
                            else -> null
                        } }.toSet() + excludeIds

                        println("loading from ${loader}, excluding $excludes")
                        loader
                                .loadAll(excludes)
                                .map { it.suspendFlatMap { t ->
                                    getId(t).map { it to t }
                                } } + ongoing
                    }
                    .map { it.map { it.second } }
}