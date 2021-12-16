
package dandd.character.automation.models.classes.levels

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class DiceCountDiceValue(
    val dice_count: Int,
    val dice_value: Int
) {
    companion object {
        val resourceTypeName = "classes-levels"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, DiceCountDiceValue> =
            json.parse {
                obj {
                    DiceCountDiceValue(
                        "dice_count".int(),
                        "dice_value".int()
                    )
                }
            }
        
    }
}
