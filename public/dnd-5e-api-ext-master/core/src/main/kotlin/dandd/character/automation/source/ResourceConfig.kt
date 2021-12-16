package dandd.character.automation.source


data class ResourceConfig<T, ID: Any>(
        val resourceNames: List<String>,
        val className: String,
        val resourceLoader: ResourceLoader<T, ID>)