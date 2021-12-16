
package dandd.character.automation.models.subraces

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class AbilityBonuses(
    val name: String,
    val url: String,
    val bonus: Int
) {
    companion object {
        val resourceTypeName = "subraces"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, AbilityBonuses> =
            json.parse {
                obj {
                    AbilityBonuses(
                        "name".str(),
                        "url".str(),
                        "bonus".int()
                    )
                }
            }
        
    }
}
