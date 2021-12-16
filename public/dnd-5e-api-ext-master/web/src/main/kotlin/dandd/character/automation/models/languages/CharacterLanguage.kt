
package dandd.character.automation.models.languages

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterLanguage(
    val _id: String,
    val index: String,
    val name: String,
    val desc: String?,
    val type: String,
    val typical_speakers: List<String>,
    val script: String?,
    val url: String
) {
    companion object {
        val resourceTypeName = "languages"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterLanguage> =
            json.parse {
                obj {
                    CharacterLanguage(
                        "_id".str(),
                        "index".str(),
                        "name".str(),
                        "desc".nullable {
                            str()
                        },
                        "type".str(),
                        "typical_speakers".arr {
                            str()
                        },
                        "script".nullable {
                            str()
                        },
                        "url".str()
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterLanguage> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterLanguage(languages: String) = 
            getResourceByUri("/api/languages/${languages}")
    }

    }
}
