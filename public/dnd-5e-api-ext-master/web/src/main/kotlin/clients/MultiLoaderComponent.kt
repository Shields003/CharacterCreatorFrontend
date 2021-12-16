package clients

import AppResources
import appComponent
import components.collapsable
import generateElementId
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import react.*
import react.dom.*

external interface MultiLoaderProps: RProps, AppResources {
    var title: String
    var itemUrls: List<String>
}

external interface MultiLoaderState: RState {
    var items: List<Renderable<*>>?
    var loaded: Boolean?
}

fun RBuilder.renderResources(props: AppResources, title: String, urls: List<String>) =
        appComponent(MultiLoaderComponent::class, props, {
            this.title = title
            itemUrls = urls
        })

abstract class AppComponent<P, S>(props: P): RComponent<P, S>(props) where P: RProps, P: AppResources, S: RState {
    fun uniqueElementId(): String {
        return props.generateElementId()
    }

    fun RBuilder.renderResources(title: String, urls: List<String>) =
            appComponent(MultiLoaderComponent::class, props, {
                this.title = title
                itemUrls = urls
            })
}

class MultiLoaderComponent(props: MultiLoaderProps): AppComponent<MultiLoaderProps, MultiLoaderState>(props) {
    override fun MultiLoaderState.init(props: MultiLoaderProps) {
        loaded = false
    }

    override fun RBuilder.render() {
        collapsable(uniqueElementId(),
                show = state.loaded?:false,
                cardTitle = { span { +props.title } },
                beforeShow = {
                    if(state.loaded == false) {
                        props.coroutineScope.launch {
                            val fetchResults = props.itemUrls.map {
                                async {
                                    props.clients.getRenderable(it)
                                }
                            }.awaitAll()

                            val renderables = fetchResults.mapNotNull { it.right }

                            val errors = fetchResults
                                    .mapNotNull { it.left }


                            errors.forEach { println("Encounted error while getting renderer: $it") }

                            setState {
                                loaded = errors.isEmpty()
                                items = renderables
                            }
                        }
                    }
                }
            ) {
            state.items?.let {
                if(it.isEmpty()) {
                    div { +"No Resources" }
                }

                it.map { it.apply { renderResource(props) } }
            }
        }
    }

}