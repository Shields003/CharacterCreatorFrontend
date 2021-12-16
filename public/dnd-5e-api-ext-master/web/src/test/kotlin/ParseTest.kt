import dandd.character.automation.models.races.CharacterRace
import web.core.Right
import web.parse.parse
import kotlin.js.Json
import kotlin.test.*

class ParseTest {
    infix fun <T> T.mustBe(other: T) {
        assertEquals(this, other, "")
    }

    @Test
    fun parses_an_int() {
        1.parse { int() } mustBe Right(1)
    }

    @Test
    fun parses_json_object() {
        JSON.parse<Json>("{\"key\":\"value\"}").parse {
            "key".str()
        } mustBe Right("value")
    }

    @Test
    fun parses_int_value() {
        JSON.parse<Json>("{\"key\":123}").parse {
            "key".int()
        } mustBe Right(123)
    }

    @Test
    fun read_nullable_sub_field() {
        JSON.parse<Json>("{\"key\":{\"key2\":\"value\"}}").parse {

            "key".nullable {
                obj {
                    "key2".str()
                }
            }
        } mustBe Right("value")
    }
}