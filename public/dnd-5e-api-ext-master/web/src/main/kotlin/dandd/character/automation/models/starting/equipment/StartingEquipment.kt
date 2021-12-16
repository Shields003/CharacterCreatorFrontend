
package dandd.character.automation.models.starting.equipment

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class StartingEquipment(
    val item: UrlName,
    val quantity: Int
) {
    companion object {
        val resourceTypeName = "starting-equipment"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, StartingEquipment> =
            json.parse {
                obj {
                    StartingEquipment(
                        "item".obj {
                            UrlName.from(node).bind()
                        },
                        "quantity".int()
                    )
                }
            }
        
    }
}
