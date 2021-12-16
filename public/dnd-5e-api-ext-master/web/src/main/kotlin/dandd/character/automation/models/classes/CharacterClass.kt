
package dandd.character.automation.models.classes

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterClass(
    val _id: String,
    val index: String,
    val name: String,
    val hit_die: Int,
    val proficiency_choices: List<ProficiencyChoices>,
    val proficiencies: List<UrlName>,
    val saving_throws: List<UrlName>,
    val starting_equipment: UrlClass,
    val class_levels: UrlClass,
    val subclasses: List<UrlName>,
    val url: String,
    val spellcasting: UrlClass?
) {
    companion object {
        val resourceTypeName = "classes"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterClass> =
            json.parse {
                obj {
                    CharacterClass(
                        "_id".str(),
                        "index".str(),
                        "name".str(),
                        "hit_die".int(),
                        "proficiency_choices".arr {
                            obj {
                                ProficiencyChoices.from(node).bind()
                            }
                        },
                        "proficiencies".arr {
                            obj {
                                UrlName.from(node).bind()
                            }
                        },
                        "saving_throws".arr {
                            obj {
                                UrlName.from(node).bind()
                            }
                        },
                        "starting_equipment".obj {
                            UrlClass.from(node).bind()
                        },
                        "class_levels".obj {
                            UrlClass.from(node).bind()
                        },
                        "subclasses".arr {
                            obj {
                                UrlName.from(node).bind()
                            }
                        },
                        "url".str(),
                        "spellcasting".nullable {
                            obj {
                                UrlClass.from(node).bind()
                            }
                        }
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterClass> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterClass(classes: String) = 
            getResourceByUri("/api/classes/${classes}")
    }

    }
}
