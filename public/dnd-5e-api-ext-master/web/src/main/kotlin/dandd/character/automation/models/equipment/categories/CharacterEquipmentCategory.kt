
package dandd.character.automation.models.equipment.categories

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterEquipmentCategory(
    val _id: String,
    val index: String,
    val name: String,
    val equipment: List<Equipment>,
    val url: String
) {
    companion object {
        val resourceTypeName = "equipment-categories"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterEquipmentCategory> =
            json.parse {
                obj {
                    CharacterEquipmentCategory(
                        "_id".str(),
                        "index".str(),
                        "name".str(),
                        "equipment".arr {
                            obj {
                                Equipment.from(node).bind()
                            }
                        },
                        "url".str()
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterEquipmentCategory> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterEquipmentCategory(equipmentCategories: String) = 
            getResourceByUri("/api/equipment-categories/${equipmentCategories}")
    }

    }
}
