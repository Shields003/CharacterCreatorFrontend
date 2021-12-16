package dandd.character.automation

import arrow.core.Either
import com.fasterxml.jackson.databind.ObjectMapper

typealias Result<T> = Either<Throwable, T>

fun String.toMap(mapper: ObjectMapper) =
        mapper
                .readValue(this, Map::class.java)
                .mapNotNull { entry -> entry.key?.let { key -> entry.value?. let { value -> when(key) {
                    is String -> key to value
                    else -> null
                } } }}
                .toMap()

fun readResourcesDirectory(): String {
    val resourcesDirectory = System.getenv("API_RESOURCE_DIRECTORY") ?: System.getProperty("api.resource.directory") ?: "api-resources"
    return resourcesDirectory
}