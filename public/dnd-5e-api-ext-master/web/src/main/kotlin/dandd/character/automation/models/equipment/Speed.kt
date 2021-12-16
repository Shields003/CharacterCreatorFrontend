
package dandd.character.automation.models.equipment

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class Speed(
    val quantity: Double,
    val unit: String
) {
    companion object {
        val resourceTypeName = "equipment"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, Speed> =
            json.parse {
                obj {
                    Speed(
                        "quantity".double(),
                        "unit".str()
                    )
                }
            }
        
    }
}
