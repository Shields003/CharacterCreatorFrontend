import clients.Clients
import components.playerCharacter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.ButtonType
import player.StaticPlayerService
import react.*
import react.dom.*
import kotlin.browser.document
import kotlin.browser.window
import kotlinext.js.require
import kotlin.random.Random

fun main() {
    require("bootstrap")
    window.onload = {
        document.body!!.insertAdjacentHTML("afterbegin", "<div class='container' id='root'></div>")
        render(document.getElementById("root")) {
            child(App::class) {

            }
        }
    }
}

class App: RComponent<RProps, AppState>() {
    override fun AppState.init() {
        coroutineScope = MainScope()
        clients = Clients()
        random = Random.Default
        playerService = StaticPlayerService()
        playerCharacters = emptyList()
        coroutineScope.launch {
            val pCs = state.playerService.getCharacters()
            setState {
                playerCharacters = pCs
            }
        }
    }

    override fun RBuilder.render() {
        h1 {
            +"Hello, React+Kotlin/JS!"
        }

        div("alert alert-warning alert-dismissible fade show") {

            attrs {
                set("role", "alert")
            }
            strong {
                +"Whoa there Nelly!"
            }

            +" This is really important"

            button(classes = "close", type = ButtonType.button) {
                attrs {
                    set("data-dismiss", "alert")
                    set("aria-label", "Close")
                }
                span {
                    attrs {
                        set("aria-hidden", "true")
                    }
                    +"Acknowledge"
                }

            }
        }

        state.playerCharacters.forEach { model ->
            playerCharacter(state) {
                playerCharacter = model
            }
        }

        div {
            +"Fin"
        }
    }
}
