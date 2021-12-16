
package dandd.character.automation.models.starting.equipment

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class ChooseTypeFrom(
    val choose: Int,
    val type: String,
    val from: List<StartingEquipment>
) {
    companion object {
        val resourceTypeName = "starting-equipment"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, ChooseTypeFrom> =
            json.parse {
                obj {
                    ChooseTypeFrom(
                        "choose".int(),
                        "type".str(),
                        "from".arr {
                            obj {
                                StartingEquipment.from(node).bind()
                            }
                        }
                    )
                }
            }
        
    }
}
