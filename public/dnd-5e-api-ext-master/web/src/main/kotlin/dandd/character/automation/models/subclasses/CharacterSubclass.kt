
package dandd.character.automation.models.subclasses

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterSubclass(
    val _id: String,
    val index: String,
    val `class`: UrlName,
    val name: String,
    val subclass_flavor: String,
    val desc: List<String>,
    val features: List<UrlName>,
    val spells: List<Spells>?,
    val url: String
) {
    companion object {
        val resourceTypeName = "subclasses"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterSubclass> =
            json.parse {
                obj {
                    CharacterSubclass(
                        "_id".str(),
                        "index".str(),
                        "class".obj {
                            UrlName.from(node).bind()
                        },
                        "name".str(),
                        "subclass_flavor".str(),
                        "desc".arr {
                            str()
                        },
                        "features".arr {
                            obj {
                                UrlName.from(node).bind()
                            }
                        },
                        "spells".nullable {
                            arr {
                                obj {
                                    Spells.from(node).bind()
                                }
                            }
                        },
                        "url".str()
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterSubclass> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterSubclass(subclasses: String) = 
            getResourceByUri("/api/subclasses/${subclasses}")
    }

    }
}
