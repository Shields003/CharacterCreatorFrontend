
package dandd.character.automation.models.starting.equipment

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterStartingEquipment(
    val `class`: UrlName,
    val _id: String,
    val index: Int,
    val starting_equipment: List<StartingEquipment>,
    val choices_to_make: Int,
    val choice_1: List<ChooseTypeFrom>,
    val choice_2: List<ChooseTypeFrom>,
    val choice_3: List<ChooseTypeFrom>?,
    val choice_4: List<ChooseTypeFrom>?,
    val url: String,
    val choice_5: List<ChooseTypeFrom>?
) {
    companion object {
        val resourceTypeName = "starting-equipment"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterStartingEquipment> =
            json.parse {
                obj {
                    CharacterStartingEquipment(
                        "class".obj {
                            UrlName.from(node).bind()
                        },
                        "_id".str(),
                        "index".int(),
                        "starting_equipment".arr {
                            obj {
                                StartingEquipment.from(node).bind()
                            }
                        },
                        "choices_to_make".int(),
                        "choice_1".arr {
                            obj {
                                ChooseTypeFrom.from(node).bind()
                            }
                        },
                        "choice_2".arr {
                            obj {
                                ChooseTypeFrom.from(node).bind()
                            }
                        },
                        "choice_3".nullable {
                            arr {
                                obj {
                                    ChooseTypeFrom.from(node).bind()
                                }
                            }
                        },
                        "choice_4".nullable {
                            arr {
                                obj {
                                    ChooseTypeFrom.from(node).bind()
                                }
                            }
                        },
                        "url".str(),
                        "choice_5".nullable {
                            arr {
                                obj {
                                    ChooseTypeFrom.from(node).bind()
                                }
                            }
                        }
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterStartingEquipment> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterStartingEquipment(startingEquipment: String) = 
            getResourceByUri("/api/starting-equipment/${startingEquipment}")
    }

    }
}
