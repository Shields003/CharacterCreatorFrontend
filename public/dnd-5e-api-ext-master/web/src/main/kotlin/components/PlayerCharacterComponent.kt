package components

import AppResources
import clients.AppComponent
import clients.renderResources
import copyFrom
import dandd.character.automation.models.classes.CharacterClass
import dandd.character.automation.models.classes.ProficiencyChoices
import dandd.character.automation.models.classes.levels.CharacterClassLevel
import dandd.character.automation.models.features.CharacterFeature
import dandd.character.automation.models.proficiencies.CharacterProficiency
import dandd.character.automation.models.races.CharacterRace
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import player.PlayerCharacter
import react.*
import react.dom.*
import web.api.ApiClient
import web.core.liftRight
import web.core.mapLeft
import web.core.mapRight
import web.core.suspendBindRight

external interface PlayerCharacterState: RState {
    var characterRace: CharacterRace?
    var characterClasses: Map<CharacterClass, List<CharacterClassLevel>>?
}

data class HydratedCharacterClassLevel(
        val features: List<CharacterFeature>,
        val featureChoices: List<CharacterFeature>
)
external interface PlayerCharacterProps: RProps, AppResources {
    var playerCharacter: PlayerCharacter
}

fun RBuilder.playerCharacter(appResources: AppResources, handler: PlayerCharacterProps.() -> Unit): ReactElement {
    return child(PlayerCharacterComponent::class) {
        this.attrs {
            copyFrom(appResources)
            handler()
        }
    }

}

class PlayerCharacterComponent(props: PlayerCharacterProps): AppComponent<PlayerCharacterProps, PlayerCharacterState>(props) {
    override fun PlayerCharacterState.init(props: PlayerCharacterProps) {
        props.coroutineScope.launch {
            ApiClient {
                val race = props.clients.races.getCharacterRace(props.playerCharacter.race).bind()

                val classes =  props.playerCharacter.classLevels
                        .map { (k, v) -> async {
                            val playerCLass = props.clients.classes.getCharacterClass(k).bind()

                            val classLevels = (1..v).map { async {
                                val classLevel = props.clients.classLevels.getCharacterClassLevel(playerCLass.index, it.toString()).bind()
                                classLevel
                            } }.awaitAll()

                            playerCLass to classLevels
                        } }
                        .awaitAll()
                        .toMap()

                setState {
                    characterRace = race
                    characterClasses = classes
                }
            }.mapLeft { println(it) }
        }
    }

    override fun RBuilder.render() {

        h1 {
            +"Player Character"
        }

        div {
            span {
              +"HP:"
            }
            span {
                +props.playerCharacter.currentHitPoints.toString()
            }
            span { +" / "}

            span {
                +props.playerCharacter.maxHitPoints.toString()
            }

            div {
                state.characterRace?.let { model ->
                    characterRace(props) {
                        characterRace = model
                    }
                }
            }

            state.characterClasses?.forEach { (model, levels) ->
                div {
                    characterClass(props) {
                        characterClass = model
                    }
                }

                div {
                    levels.find { it.level == levels.size }?.let {
                        characterClassLevel(props) {
                            characterClassLevel = it
                        }
                    }
                }

                val levelsWithFeatures = levels.filter { it.features.isNotEmpty() }
                if(levelsWithFeatures.isNotEmpty()) {
                    h3 { +"Features" }
                    levelsWithFeatures.forEach { classLevel ->
                        if (classLevel.features.isNotEmpty()) {
                            renderResources("Level ${classLevel.level}", classLevel.features.map { it.url })
                        }
                    }
                }

                val levelsWithFeatureChoices = levels.filter { it.feature_choices.isNotEmpty() }
                if(levelsWithFeatureChoices.isNotEmpty()) {
                    h3 { +"Features Choices" }
                    levelsWithFeatureChoices.forEach { classLevel ->
                        renderResources("Level ${classLevel.level}", classLevel.feature_choices.map{ it.url })
                    }
                }

                renderResources(props, "Proficiencies", model.proficiencies.map { it.url } )

                if(model.proficiencies.isNotEmpty()) {
                    h3 { "Proficiency Choices" }
                }
                model.proficiency_choices.map {
                    renderResources(props, "Choose ${it.choose} ${it.type}", it.from.map { it.url } )
                }
            }
        }
    }
}

