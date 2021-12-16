package components

import AppResources
import appComponent
import copyFrom
import dandd.character.automation.models.classes.CharacterClass
import dandd.character.automation.models.proficiencies.CharacterProficiency
import kotlinx.coroutines.launch
import react.*
import react.dom.*
import web.core.bindRight
import web.core.liftRight
import web.core.mapRight
import kotlin.reflect.KClass


external interface CharacterClassProps: RProps, AppResources {
    var characterClass: CharacterClass
}

external interface CharacterClassState: RState {
}

fun RBuilder.characterClass(parent: AppResources, handler: CharacterClassProps.() -> Unit): ReactElement =
    appComponent(CharacterClassComponent::class, parent, handler)


class CharacterClassComponent(props: CharacterClassProps): RComponent<CharacterClassProps, CharacterClassState>(props) {

    override fun RBuilder.render() {
        div("card") {
            div("card-body") {
                div("card-title") {
                    span { strong { +"Class: "  } }
                    span { +props.characterClass.name }
                }
                div("card-text") {
                    div {
                        span { strong { +"Hit Die "  } }
                        span { +props.characterClass.hit_die.toString() }
                    }
                }
            }
        }
    }
}