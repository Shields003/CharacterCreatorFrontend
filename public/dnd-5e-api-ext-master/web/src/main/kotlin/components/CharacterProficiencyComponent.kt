package components

import AppResources
import appComponent
import copyFrom
import dandd.character.automation.models.proficiencies.CharacterProficiency
import react.*
import react.dom.*


external interface CharacterProficiencyProps: RProps, AppResources {
    var characterProficiency: CharacterProficiency
}

external interface CharacterProficiencyState: RState

fun RBuilder.characterProficiency(parent: AppResources, handler: CharacterProficiencyProps.() -> Unit): ReactElement =
        appComponent(CharacterProficiencyComponent::class, parent, handler)

class CharacterProficiencyComponent(props: CharacterProficiencyProps): RComponent<CharacterProficiencyProps, CharacterProficiencyState>(props) {
    override fun CharacterProficiencyState.init(props: CharacterProficiencyProps) {

    }

    override fun RBuilder.render() {
        div {
            div {
                span { strong { +props.characterProficiency.name } }
            }
            div {
                +props.characterProficiency.url
            }
        }
    }
}

