package components

import kotlinx.html.*
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.dom.RDOMBuilder
import react.dom.a
import react.dom.button
import react.dom.div
import kotlin.browser.document

typealias Builder<T> = RDOMBuilder<T>.() ->Unit

fun RBuilder.card(
        titleTag: Builder<DIV>? = null,
        cardTextTag: Builder<DIV>? = null,
        cardBodyContent: Builder<DIV>? = null,
        subTitleTag: Builder<DIV>? = null,
        cardLinks: Map<String, Builder<A>> = emptyMap(),
        cardHeader: Builder<DIV>? = null,
        cardFooter: Builder<DIV>? = null
) {
    div("card") {
        cardHeader?.let { div("card-header", it)}
        div("card-body") {
            titleTag?.let { div("card-title", it) }
            subTitleTag?.let { div("card-subtitle", it)}
            cardTextTag?.let { div("card-text", it) }
            cardBodyContent?.invoke(this)
            cardLinks.forEach { (href, tagBuilder) ->
                a(href = href, classes = "card-link", block = tagBuilder)
            }
        }
        cardFooter?.let { div("card-footer text-muted", it)}
    }
}

fun RBuilder.collapsable(
        elementId: String,
        show: Boolean = false,
        cardTitle: Builder<BUTTON> = { },
        beforeShow: (Boolean) -> Unit = { },
        afterShow: (Boolean) -> Unit = { },
        body: Builder<DIV>
) {
    div {
        var isVisible = show
        card(
                cardHeader = {
                    button(classes = "btn btn-primary",
                            type = ButtonType.button) {
                        attrs {
                            id = "$elementId-button"
                            onClickFunction = {
                                isVisible = !isVisible
                                val clazz = if (isVisible) "show" else "collapse"
                                beforeShow(isVisible)
                                document.getElementById(elementId)
                                        ?.className = clazz
                                document.getElementById("$elementId-button")
                                        ?.className =  if(isVisible) "btn btn-primary" else "btn btn-primary text-muted"
                                afterShow(isVisible)
                            }
                        }
                        cardTitle()
                    }
                },
                cardBodyContent = {
                    val clazz = if(isVisible) { "show" } else { "collapse" }
                    div(clazz) {
                        attrs {
                            id = elementId
                        }
                        body()
                    }
                })
    }
}