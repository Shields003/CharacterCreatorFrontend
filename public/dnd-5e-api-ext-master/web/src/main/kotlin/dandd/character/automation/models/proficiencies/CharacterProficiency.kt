
package dandd.character.automation.models.proficiencies

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterProficiency(
    val _id: String,
    val index: String,
    val type: String,
    val name: String,
    val classes: List<UrlName>,
    val races: List<UrlName>,
    val url: String
) {
    companion object {
        val resourceTypeName = "proficiencies"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterProficiency> =
            json.parse {
                obj {
                    CharacterProficiency(
                        "_id".str(),
                        "index".str(),
                        "type".str(),
                        "name".str(),
                        "classes".arr {
                            obj {
                                UrlName.from(node).bind()
                            }
                        },
                        "races".arr {
                            obj {
                                UrlName.from(node).bind()
                            }
                        },
                        "url".str()
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterProficiency> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterProficiency(proficiencies: String) = 
            getResourceByUri("/api/proficiencies/${proficiencies}")
    }

    }
}
