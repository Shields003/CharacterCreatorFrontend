
package dandd.character.automation.models.races

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class AbilityBonusOptions(
    val choose: Int,
    val type: String,
    val from: List<AbilityBonuses>
) {
    companion object {
        val resourceTypeName = "races"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, AbilityBonusOptions> =
            json.parse {
                obj {
                    AbilityBonusOptions(
                        "choose".int(),
                        "type".str(),
                        "from".arr {
                            obj {
                                AbilityBonuses.from(node).bind()
                            }
                        }
                    )
                }
            }
        
    }
}
