
package dandd.character.automation.models.equipment

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class DamageDiceDamageType(
    val damage_dice: String,
    val damage_type: NameUrl
) {
    companion object {
        val resourceTypeName = "equipment"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, DamageDiceDamageType> =
            json.parse {
                obj {
                    DamageDiceDamageType(
                        "damage_dice".str(),
                        "damage_type".obj {
                            NameUrl.from(node).bind()
                        }
                    )
                }
            }
        
    }
}
