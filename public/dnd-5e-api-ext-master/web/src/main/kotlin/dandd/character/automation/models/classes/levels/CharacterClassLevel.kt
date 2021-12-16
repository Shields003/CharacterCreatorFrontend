
package dandd.character.automation.models.classes.levels

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterClassLevel(
    val _id: String,
    val level: Int,
    val ability_score_bonuses: Int,
    val prof_bonus: Int,
    val feature_choices: List<NameUrl>,
    val features: List<NameUrl>,
    val class_specific: ClassSpecific,
    val index: Int,
    val `class`: NameUrl,
    val url: String,
    val spellcasting: Spellcasting?
) {
    companion object {
        val resourceTypeName = "classes-levels"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterClassLevel> =
            json.parse {
                obj {
                    CharacterClassLevel(
                        "_id".str(),
                        "level".int(),
                        "ability_score_bonuses".int(),
                        "prof_bonus".int(),
                        "feature_choices".arr {
                            obj {
                                NameUrl.from(node).bind()
                            }
                        },
                        "features".arr {
                            obj {
                                NameUrl.from(node).bind()
                            }
                        },
                        "class_specific".obj {
                            ClassSpecific.from(node).bind()
                        },
                        "index".int(),
                        "class".obj {
                            NameUrl.from(node).bind()
                        },
                        "url".str(),
                        "spellcasting".nullable {
                            obj {
                                Spellcasting.from(node).bind()
                            }
                        }
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterClassLevel> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterClassLevel(classes: String, levels: String) = 
            getResourceByUri("/api/classes/${classes}/levels/${levels}")
    }

    }
}
