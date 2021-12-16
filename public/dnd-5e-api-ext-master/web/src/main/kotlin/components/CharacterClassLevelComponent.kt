package components

import AppResources
import appComponent
import copyFrom
import dandd.character.automation.models.classes.levels.CharacterClassLevel
import dandd.character.automation.models.classes.levels.Spellcasting
import kotlinx.html.ThScope
import react.*
import react.dom.*
import kotlin.reflect.KClass


external interface CharacterClassLevelProps: RProps, AppResources {
    var characterClassLevel: CharacterClassLevel
}

external interface CharacterClassLevelState: RState { }

fun RBuilder.characterClassLevel(parent: AppResources, handler: CharacterClassLevelProps.() -> Unit): ReactElement =
    appComponent(CharacterClassLevelComponent::class, parent, handler)

class CharacterClassLevelComponent(props: CharacterClassLevelProps): RComponent<CharacterClassLevelProps, CharacterClassLevelState>(props) {
    override fun RBuilder.render() {
        div {
            div {
                span { strong { +"Proficiency Bonus "  } }
                span { +props.characterClassLevel.prof_bonus.toString() }
            }

            props.characterClassLevel.spellcasting
                    ?.let {
                        h5 { +"Spellcasting" }
                        renderSpellCasting(it)
                    }
        }
    }

    fun RBuilder.renderSpellCasting(spellcasting: Spellcasting) {
        div {
            span { strong { +"Cantrips Known " } }
            span { +spellcasting.cantrips_known.toString() }
        }
        div {
            span { strong { +"Spells Known " } }
            span { +spellcasting.spells_known.toString() }
        }

        val spellsSlots =  listOf(spellcasting.spell_slots_level_1,
                spellcasting.spell_slots_level_2,
                spellcasting.spell_slots_level_3,
                spellcasting.spell_slots_level_4,
                spellcasting.spell_slots_level_5,
                spellcasting.spell_slots_level_6,
                spellcasting.spell_slots_level_7,
                spellcasting.spell_slots_level_8,
                spellcasting.spell_slots_level_9)
                .map { it?:0 }

        table("table") {
            thead("thead-light") {
                tr {
                    th(ThScope.col) { +"Level" }
                    spellsSlots.mapIndexed { index, _ ->
                        th(ThScope.col) {  +"${index + 1}"}
                    }
                }

            }
            tbody {
                tr {
                    th(scope = ThScope.row) { "+Spells Known"}
                    spellsSlots.map { numKnown ->
                        td { +numKnown.toString() }
                    }
                }
            }
        }
    }
}