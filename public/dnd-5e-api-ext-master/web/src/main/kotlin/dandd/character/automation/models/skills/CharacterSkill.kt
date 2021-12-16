
package dandd.character.automation.models.skills

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterSkill(
    val _id: String,
    val index: String,
    val name: String,
    val desc: List<String>,
    val ability_score: AbilityScore,
    val url: String
) {
    companion object {
        val resourceTypeName = "skills"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterSkill> =
            json.parse {
                obj {
                    CharacterSkill(
                        "_id".str(),
                        "index".str(),
                        "name".str(),
                        "desc".arr {
                            str()
                        },
                        "ability_score".obj {
                            AbilityScore.from(node).bind()
                        },
                        "url".str()
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterSkill> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterSkill(skills: String) = 
            getResourceByUri("/api/skills/${skills}")
    }

    }
}
