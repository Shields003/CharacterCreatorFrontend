package dandd.character.automation.source

import dandd.character.automation.Result

interface ResourceLoader<T, ID: Any> {
    suspend fun loadAll(excludeIds: Set<ID>): List<Result<T>>
    suspend fun loadAll(): List<Result<T>>
    suspend fun loadResource(id: ID): Result<T>
}

data class ResourceLoaderFactory(val urlBase: String,
                                 val resourcesBaseDirectory: String) {
    fun <T> createLoaderFor(
            resourceName: String,
            readingMapper: suspend (String) -> Result<T>,
            writingMapper: suspend (T) -> Result<String>,
            getId: suspend (T) -> Result<String>
    ): ResourceLoader<T, String> {
        val fileLoader = simpleFileReesourceLoader(resourceName, resourcesBaseDirectory, readingMapper, writingMapper)
        val httpLoader = PersistingOnSuccessResourceLoader(
                simpleHttpResourceLoader(urlBase, resourceName, readingMapper),
                fileLoader,
                getId)

        return MultiResourceLoader(
                listOf(
                        fileLoader,
                        httpLoader),
                getId)
    }

    fun <T> createNestedLoaderFor(
            resourceName: String,
            subResourceType: String,
            readingMapper: suspend (String) -> Result<T>,
            writingMapper: suspend (T) -> Result<String>,
            getId: suspend (T) -> Result<Pair<String, String>>
    ): ResourceLoader<T, Pair<String, String>> {

        val fileLoader = nestedResourceFileLoader(resourceName, subResourceType, resourcesBaseDirectory, readingMapper, writingMapper)
        val httpLoader = PersistingOnSuccessResourceLoader(
                nestedHttpResourceLoader(urlBase, resourceName, subResourceType, readingMapper),
                fileLoader,
                getId)

        return MultiResourceLoader(
                listOf(
                        fileLoader,
                        httpLoader),
                getId)
    }
}
//
//fun <T> createLoaderFor(
//        urlBase: String,
//        resourcesBaseDirectory: String,
//        resourceName: String,
//        readingMapper: suspend (String) -> Result<T>,
//        writingMapper: suspend (T) -> Result<String>,
//        getId: suspend (T) -> Result<String>
//): ResourceLoader<T, String> {
//    val fileLoader = simpleFileReesourceLoader(resourceName, resourcesBaseDirectory, readingMapper, writingMapper)
//    val httpLoader = PersistingOnSuccessResourceLoader(
//            simpleHttpResourceLoader(urlBase, resourceName, readingMapper),
//            fileLoader,
//            getId)
//
//    return MultiResourceLoader(
//            listOf(
//                    fileLoader,
//                    httpLoader),
//            getId)
//}
//
//fun <T> createNestedLoaderFor(
//        urlBase: String,
//        resourcesBaseDirectory: String,
//        resourceName: String,
//        readingMapper: suspend (String) -> Result<T>,
//        writingMapper: suspend (T) -> Result<String>,
//        getId: suspend (T) -> Result<Pair<String, String>>,
//        subResourceType: String
//): ResourceLoader<T, Pair<String, String>> {
//
//    val fileLoader = nestedResourceFileLoader(resourceName, subResourceType, resourcesBaseDirectory, readingMapper, writingMapper)
//    val httpLoader = PersistingOnSuccessResourceLoader(
//            nestedHttpResourceLoader(urlBase, resourceName, subResourceType, readingMapper),
//            fileLoader,
//            getId)
//
//    return MultiResourceLoader(
//            listOf(
//                    fileLoader,
//                    httpLoader),
//            getId)
//}