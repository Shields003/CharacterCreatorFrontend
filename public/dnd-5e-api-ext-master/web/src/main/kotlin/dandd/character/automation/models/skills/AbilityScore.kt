
package dandd.character.automation.models.skills

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class AbilityScore(
    val url: String,
    val name: String
) {
    companion object {
        val resourceTypeName = "skills"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, AbilityScore> =
            json.parse {
                obj {
                    AbilityScore(
                        "url".str(),
                        "name".str()
                    )
                }
            }
        
    }
}
