
package dandd.character.automation.models.equipment

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class ArmorClass(
    val base: Int,
    val dex_bonus: Boolean,
    val max_bonus: Int?
) {
    companion object {
        val resourceTypeName = "equipment"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, ArmorClass> =
            json.parse {
                obj {
                    ArmorClass(
                        "base".int(),
                        "dex_bonus".boolean(),
                        "max_bonus".nullable {
                            int()
                        }
                    )
                }
            }
        
    }
}
