
package dandd.character.automation.models.subclasses

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class Spells(
    val prerequisites: List<Prerequisites>,
    val spell: UrlName
) {
    companion object {
        val resourceTypeName = "subclasses"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, Spells> =
            json.parse {
                obj {
                    Spells(
                        "prerequisites".arr {
                            obj {
                                Prerequisites.from(node).bind()
                            }
                        },
                        "spell".obj {
                            UrlName.from(node).bind()
                        }
                    )
                }
            }
        
    }
}
