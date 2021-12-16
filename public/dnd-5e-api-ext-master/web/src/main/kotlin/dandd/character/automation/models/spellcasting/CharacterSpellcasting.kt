
package dandd.character.automation.models.spellcasting

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterSpellcasting(
    val `class`: UrlName,
    val _id: String,
    val index: String,
    val level: Int,
    val spellcasting_ability: UrlName,
    val info: List<Info>,
    val url: String
) {
    companion object {
        val resourceTypeName = "spellcasting"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterSpellcasting> =
            json.parse {
                obj {
                    CharacterSpellcasting(
                        "class".obj {
                            UrlName.from(node).bind()
                        },
                        "_id".str(),
                        "index".str(),
                        "level".int(),
                        "spellcasting_ability".obj {
                            UrlName.from(node).bind()
                        },
                        "info".arr {
                            obj {
                                Info.from(node).bind()
                            }
                        },
                        "url".str()
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterSpellcasting> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterSpellcasting(spellcasting: String) = 
            getResourceByUri("/api/spellcasting/${spellcasting}")
    }

    }
}
