package dandd.character.automation.source

import dandd.character.automation.Result

internal interface ResourcePersister<T, ID: Any> {
    suspend fun persistResource(id: ID, resource: T): Result<T>
}
