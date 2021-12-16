
package dandd.character.automation.models.spells

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class NameUrl(
    val name: String,
    val url: String
) {
    companion object {
        val resourceTypeName = "spells"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, NameUrl> =
            json.parse {
                obj {
                    NameUrl(
                        "name".str(),
                        "url".str()
                    )
                }
            }
        
    }
}
