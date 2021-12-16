
package dandd.character.automation.models.spells

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterSpell(
    val _id: String,
    val index: String,
    val name: String,
    val desc: List<String>,
    val range: String,
    val components: List<String>,
    val material: String?,
    val ritual: Boolean,
    val duration: String,
    val concentration: Boolean,
    val casting_time: String,
    val level: Int,
    val school: NameUrl,
    val classes: List<NameUrl>,
    val subclasses: List<NameUrl>,
    val url: String,
    val higher_level: List<String>?
) {
    companion object {
        val resourceTypeName = "spells"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterSpell> =
            json.parse {
                obj {
                    CharacterSpell(
                        "_id".str(),
                        "index".str(),
                        "name".str(),
                        "desc".arr {
                            str()
                        },
                        "range".str(),
                        "components".arr {
                            str()
                        },
                        "material".nullable {
                            str()
                        },
                        "ritual".boolean(),
                        "duration".str(),
                        "concentration".boolean(),
                        "casting_time".str(),
                        "level".int(),
                        "school".obj {
                            NameUrl.from(node).bind()
                        },
                        "classes".arr {
                            obj {
                                NameUrl.from(node).bind()
                            }
                        },
                        "subclasses".arr {
                            obj {
                                NameUrl.from(node).bind()
                            }
                        },
                        "url".str(),
                        "higher_level".nullable {
                            arr {
                                str()
                            }
                        }
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterSpell> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterSpell(spells: String) = 
            getResourceByUri("/api/spells/${spells}")
    }

    }
}
