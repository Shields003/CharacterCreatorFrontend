
package dandd.character.automation.models.classes

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class Spellcasting(
    val spells_known: Int?,
    val spell_slots_level_1: Int?,
    val spell_slots_level_2: Int?,
    val spell_slots_level_3: Int?,
    val spell_slots_level_4: Int?,
    val spell_slots_level_5: Int?,
    val cantrips_known: Int?,
    val spell_slots_level_6: Int?,
    val spell_slots_level_7: Int?,
    val spell_slots_level_8: Int?,
    val spell_slots_level_9: Int?,
    val url: String?,
    val `class`: String?
) {
    companion object {
        val resourceTypeName = "classes"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, Spellcasting> =
            json.parse {
                obj {
                    Spellcasting(
                        "spells_known".nullable {
                            int()
                        },
                        "spell_slots_level_1".nullable {
                            int()
                        },
                        "spell_slots_level_2".nullable {
                            int()
                        },
                        "spell_slots_level_3".nullable {
                            int()
                        },
                        "spell_slots_level_4".nullable {
                            int()
                        },
                        "spell_slots_level_5".nullable {
                            int()
                        },
                        "cantrips_known".nullable {
                            int()
                        },
                        "spell_slots_level_6".nullable {
                            int()
                        },
                        "spell_slots_level_7".nullable {
                            int()
                        },
                        "spell_slots_level_8".nullable {
                            int()
                        },
                        "spell_slots_level_9".nullable {
                            int()
                        },
                        "url".nullable {
                            str()
                        },
                        "class".nullable {
                            str()
                        }
                    )
                }
            }
        
    }
}
