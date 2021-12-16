
package dandd.character.automation.models.features

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class Prerequisites(
    val type: String,
    val proficiency: String?,
    val level: Int?,
    val feature: String?,
    val spell: String?
) {
    companion object {
        val resourceTypeName = "features"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, Prerequisites> =
            json.parse {
                obj {
                    Prerequisites(
                        "type".str(),
                        "proficiency".nullable {
                            str()
                        },
                        "level".nullable {
                            int()
                        },
                        "feature".nullable {
                            str()
                        },
                        "spell".nullable {
                            str()
                        }
                    )
                }
            }
        
    }
}
