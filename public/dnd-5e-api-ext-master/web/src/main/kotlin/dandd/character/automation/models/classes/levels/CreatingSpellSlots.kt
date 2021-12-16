
package dandd.character.automation.models.classes.levels

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CreatingSpellSlots(
    val spell_slot_level: Int,
    val sorcery_point_cost: Int
) {
    companion object {
        val resourceTypeName = "classes-levels"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CreatingSpellSlots> =
            json.parse {
                obj {
                    CreatingSpellSlots(
                        "spell_slot_level".int(),
                        "sorcery_point_cost".int()
                    )
                }
            }
        
    }
}
