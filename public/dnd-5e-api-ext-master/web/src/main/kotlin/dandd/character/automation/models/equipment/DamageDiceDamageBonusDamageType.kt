
package dandd.character.automation.models.equipment

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class DamageDiceDamageBonusDamageType(
    val damage_dice: String,
    val damage_bonus: Int,
    val damage_type: NameUrl
) {
    companion object {
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, DamageDiceDamageBonusDamageType> =
            json.parse {
                obj {
                    DamageDiceDamageBonusDamageType(
                        "damage_dice".str(),
                        "damage_bonus".int(),
                        "damage_type".obj {
                            NameUrl.from(node).bind()
                        }
                    )
                }
            }
        
    }
}
