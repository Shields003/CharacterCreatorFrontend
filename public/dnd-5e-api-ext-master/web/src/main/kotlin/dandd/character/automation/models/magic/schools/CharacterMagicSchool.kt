
package dandd.character.automation.models.magic.schools

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterMagicSchool(
    val _id: String,
    val index: String,
    val name: String,
    val desc: String,
    val url: String
) {
    companion object {
        val resourceTypeName = "magic-schools"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterMagicSchool> =
            json.parse {
                obj {
                    CharacterMagicSchool(
                        "_id".str(),
                        "index".str(),
                        "name".str(),
                        "desc".str(),
                        "url".str()
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterMagicSchool> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterMagicSchool(magicSchools: String) = 
            getResourceByUri("/api/magic-schools/${magicSchools}")
    }

    }
}
