
package dandd.character.automation.models.classes

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class ClassSpecific(
    val rage_count: Int?,
    val rage_damage_bonus: Int?,
    val brutal_critical_dice: Int?,
    val favored_enemies: Int?,
    val favored_terrain: Int?,
    val sorcery_points: Int?,
    val metamagic_known: Int?,
    val creating_spell_slots: List<CreatingSpellSlots>?,
    val action_surges: Int?,
    val indomitable_uses: Int?,
    val extra_attacks: Int?,
    val invocations_known: Int?,
    val mystic_arcanum_level_6: Int?,
    val mystic_arcanum_level_7: Int?,
    val mystic_arcanum_level_8: Int?,
    val mystic_arcanum_level_9: Int?,
    val martial_arts: DiceCountDiceValue?,
    val ki_points: Int?,
    val unarmored_movement: Int?,
    val bardic_inspiration_die: Int?,
    val song_of_rest_die: Int?,
    val magical_secrets_max_5: Int?,
    val magical_secrets_max_7: Int?,
    val magical_secrets_max_9: Int?,
    val aura_range: Int?,
    val wild_shape_max_cr: Double?,
    val wild_shape_swim: Boolean?,
    val wild_shape_fly: Boolean?,
    val arcane_recovery_levels: Int?,
    val sneak_attack: DiceCountDiceValue?,
    val channel_divinity_charges: Int?,
    val destroy_undead_cr: Double?
) {
    companion object {
        val resourceTypeName = "classes"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, ClassSpecific> =
            json.parse {
                obj {
                    ClassSpecific(
                        "rage_count".nullable {
                            int()
                        },
                        "rage_damage_bonus".nullable {
                            int()
                        },
                        "brutal_critical_dice".nullable {
                            int()
                        },
                        "favored_enemies".nullable {
                            int()
                        },
                        "favored_terrain".nullable {
                            int()
                        },
                        "sorcery_points".nullable {
                            int()
                        },
                        "metamagic_known".nullable {
                            int()
                        },
                        "creating_spell_slots".nullable {
                            arr {
                                obj {
                                    CreatingSpellSlots.from(node).bind()
                                }
                            }
                        },
                        "action_surges".nullable {
                            int()
                        },
                        "indomitable_uses".nullable {
                            int()
                        },
                        "extra_attacks".nullable {
                            int()
                        },
                        "invocations_known".nullable {
                            int()
                        },
                        "mystic_arcanum_level_6".nullable {
                            int()
                        },
                        "mystic_arcanum_level_7".nullable {
                            int()
                        },
                        "mystic_arcanum_level_8".nullable {
                            int()
                        },
                        "mystic_arcanum_level_9".nullable {
                            int()
                        },
                        "martial_arts".nullable {
                            obj {
                                DiceCountDiceValue.from(node).bind()
                            }
                        },
                        "ki_points".nullable {
                            int()
                        },
                        "unarmored_movement".nullable {
                            int()
                        },
                        "bardic_inspiration_die".nullable {
                            int()
                        },
                        "song_of_rest_die".nullable {
                            int()
                        },
                        "magical_secrets_max_5".nullable {
                            int()
                        },
                        "magical_secrets_max_7".nullable {
                            int()
                        },
                        "magical_secrets_max_9".nullable {
                            int()
                        },
                        "aura_range".nullable {
                            int()
                        },
                        "wild_shape_max_cr".nullable {
                            double()
                        },
                        "wild_shape_swim".nullable {
                            boolean()
                        },
                        "wild_shape_fly".nullable {
                            boolean()
                        },
                        "arcane_recovery_levels".nullable {
                            int()
                        },
                        "sneak_attack".nullable {
                            obj {
                                DiceCountDiceValue.from(node).bind()
                            }
                        },
                        "channel_divinity_charges".nullable {
                            int()
                        },
                        "destroy_undead_cr".nullable {
                            double()
                        }
                    )
                }
            }
        
    }
}
