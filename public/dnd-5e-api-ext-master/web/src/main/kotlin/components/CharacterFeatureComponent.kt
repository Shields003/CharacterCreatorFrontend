package components

import AppResources
import appComponent
import clients.AppComponent
import dandd.character.automation.models.features.CharacterFeature
import kotlinx.html.*
import react.*
import react.dom.*

external interface CharacterFeatureProps: RProps, AppResources {
    var feature: CharacterFeature
}

external interface CharacterFeatureState: RState {
}

fun RBuilder.characterFeature(parent: AppResources, handler: CharacterFeatureProps.() -> Unit): ReactElement =
        appComponent(CharacterFeatureComponent::class, parent, handler)

class CharacterFeatureComponent(props: CharacterFeatureProps): AppComponent<CharacterFeatureProps, CharacterFeatureState>(props) {
    override fun RBuilder.render() {
        div {
            div { strong { +props.feature.name } }
            props.feature.group?.let { group ->
                div {
                    span { strong { +"Group " } }
                    span { +group }
                }
            }
            props.feature.prerequisites?.let {
                div { strong { +"Prerequisites" } }
                it.map {
                    it.type.let { label("Type", it)}
                    it.level?.let { label("Level", it.toString()) }
                    it.feature?.let { label("Feature", it) }
                    it.proficiency?.let { label("Proficiency", it)}
                    it.spell?.let { label("Spell", it) }
                }
            }

            props.feature.desc.map {
                div { +it }
            }

            props.feature.choice?.let { choice ->
                renderResources("Choose ${choice.choose} ${choice.type}", choice.from.map { it.url })
            }
        }
    }
}

fun <E: Tag> RDOMBuilder<E>.label(name: String, value: String) {
    div {
        span { strong { +name } }
        span { +value }
    }
}

