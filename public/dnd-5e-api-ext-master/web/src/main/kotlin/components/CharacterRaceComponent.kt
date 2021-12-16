package components

import AppResources
import copyFrom
import dandd.character.automation.models.races.CharacterRace
import react.*
import react.dom.div
import react.dom.h2
import react.dom.span

external interface CharacterRaceProps: RProps, AppResources {
    var characterRace: CharacterRace
}

fun RBuilder.characterRace(parent: AppResources, handler: CharacterRaceProps.() -> Unit): ReactElement {
    return child(CharacterRaceComponent::class) {
        attrs {
            copyFrom(parent)
            handler()
        }
    }
}

class CharacterRaceComponent: RComponent<CharacterRaceProps, RState>() {
    override fun RBuilder.render() {

        h2 {
            +"Race"
        }
        div {
            span {
               +"name: "
            }

            span {
                +props.characterRace.name
            }
        }
    }
}