
package dandd.character.automation.models.spellcasting

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class Info(
    val name: String,
    val desc: List<String>
) {
    companion object {
        val resourceTypeName = "spellcasting"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, Info> =
            json.parse {
                obj {
                    Info(
                        "name".str(),
                        "desc".arr {
                            str()
                        }
                    )
                }
            }
        
    }
}
