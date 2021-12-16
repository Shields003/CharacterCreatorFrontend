
package dandd.character.automation.models.classes

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class ProficiencyChoices(
    val choose: Int,
    val type: String,
    val from: List<UrlName>
) {
    companion object {
        val resourceTypeName = "classes"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, ProficiencyChoices> =
            json.parse {
                obj {
                    ProficiencyChoices(
                        "choose".int(),
                        "type".str(),
                        "from".arr {
                            obj {
                                UrlName.from(node).bind()
                            }
                        }
                    )
                }
            }
        
    }
}
