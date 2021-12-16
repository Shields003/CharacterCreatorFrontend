
package dandd.character.automation.models.proficiencies

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class UrlName(
    val url: String,
    val name: String
) {
    companion object {
        val resourceTypeName = "proficiencies"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, UrlName> =
            json.parse {
                obj {
                    UrlName(
                        "url".str(),
                        "name".str()
                    )
                }
            }
        
    }
}
