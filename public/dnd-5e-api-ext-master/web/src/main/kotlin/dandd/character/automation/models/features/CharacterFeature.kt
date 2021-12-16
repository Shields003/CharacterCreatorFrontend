
package dandd.character.automation.models.features

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterFeature(
    val `class`: UrlName,
    val desc: List<String>,
    val _id: String,
    val index: String,
    val name: String,
    val level: Int?,
    val url: String,
    val subclass: UrlName?,
    val prerequisites: List<Prerequisites>?,
    val group: String?,
    val choice: Choice?,
    val reference: String?
) {
    companion object {
        val resourceTypeName = "features"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterFeature> =
            json.parse {
                obj {
                    CharacterFeature(
                        "class".obj {
                            UrlName.from(node).bind()
                        },
                        "desc".arr {
                            str()
                        },
                        "_id".str(),
                        "index".str(),
                        "name".str(),
                        "level".nullable {
                            int()
                        },
                        "url".str(),
                        "subclass".nullable {
                            obj {
                                UrlName.from(node).bind()
                            }
                        },
                        "prerequisites".nullable {
                            arr {
                                obj {
                                    Prerequisites.from(node).bind()
                                }
                            }
                        },
                        "group".nullable {
                            str()
                        },
                        "choice".nullable {
                            obj {
                                Choice.from(node).bind()
                            }
                        },
                        "reference".nullable {
                            str()
                        }
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterFeature> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterFeature(features: String) = 
            getResourceByUri("/api/features/${features}")
    }

    }
}
