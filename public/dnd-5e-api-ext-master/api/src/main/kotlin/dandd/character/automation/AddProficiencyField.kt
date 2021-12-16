package dandd.character.automation

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

data class UrlName(val url: String, val name: String)
data class UrlTypeName(val url: String, val type: String, val name: String)
data class Proficiency(
        val index: String,
        val type: String,
        val name: String,
        val classes: List<UrlName>,
        val races: List<UrlName>,
        val url: String,
        val references: List<UrlTypeName>?= null
)
fun main() {
    val objectMapper = jacksonObjectMapper()

    val proficiencies = objectMapper
            .readValue<List<Proficiency>>(File("proficiencies.json").readText())
            .map {
                when(it.type) {
                    "Skills" -> it.addSkillReference()
                    "Saving Throws" -> it.addSavingThrowReference()
                    "Musical Instruments" -> it.addMusicalInstrumentReference()
                    "Other" -> it.addOtherProficiency()
                    "Vehicles" -> it.addVehiclesProficiency()
                    "Armor" -> it.addArmorReference()
                    "Weapons" -> it.addWeaponReference()
                    "Artisan's Tools" -> it.addArtisansToolsReference()
                    "Gaming Sets" -> it.addGamingSetsReference()
                    else -> it
                }
            }



    println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(proficiencies))
    println(proficiencies.size)
    println(proficiencies.count { it.references == null })



}

fun Proficiency.addSkillReference(): Proficiency =
    this.copy(
        references = listOf(
                UrlTypeName(
                        url = "/api/skills/${index.removePrefix("skill-")}",
                        type = "skills",
                        name = name.removePrefix("Skill: "))))

fun Proficiency.addSavingThrowReference(): Proficiency =
    this.copy(references = listOf(
            UrlTypeName(
                    url = "/api/ability-scores/${index.removePrefix("saving-throw-")}",
                    type = "ability-score",
                    name = name.removePrefix("Saving Throw: ")
            )
    ))

fun Proficiency.addMusicalInstrumentReference(): Proficiency =
    this.copy(references = listOf(
            UrlTypeName(
                    url = "/api/equipment/$index",
                    type = "equipment",
                    name = name
            )
    ))

fun Proficiency.addOtherProficiency(): Proficiency =
        this.copy(references = listOf(
                UrlTypeName(
                        url = "/api/equipment/$index",
                        type = "equipment",
                        name = name
                )
        ))

fun Proficiency.addVehiclesProficiency(): Proficiency =
        this.copy(references = listOf(
                UrlTypeName(
                        url = "/api/equipment-categories/$index",
                        type = "equipment-categories",
                        name = name
                )
        ))
fun Proficiency.addArmorReference(): Proficiency {
    val resource = if(
            index == "light-armor" ||
            index == "medium-armor" ||
            index == "heavy-armor" ||
            index == "all-armor") {
        "equipment-categories"
    } else "equipment"

    val index = if(index == "all-armor") {
        "armor"
    } else {
        index
    }
    return this.copy(references = listOf(
            UrlTypeName(
                    url = "/api/$resource/$index",
                    type = resource,
                    name = name
            )
    ))
}


fun Proficiency.addWeaponReference(): Proficiency {
    val resource = if(".*-weapons".toRegex().matches(index)) {
        "equipment-categories"
    } else "equipment"

    val index = if(resource == "equipment") {
        index.removeSuffix("s")
    } else {
        index
    }
    return this.copy(references = listOf(
            UrlTypeName(
                    url = "/api/$resource/$index",
                    type = resource,
                    name = name
            )
    ))
}

fun Proficiency.addArtisansToolsReference(): Proficiency =
    this.copy(references = listOf(
            UrlTypeName(
                    url = "/api/equipment/$index",
                    type = "equipment",
                    name = name
            )))

fun Proficiency.addGamingSetsReference(): Proficiency =
    this.copy(references = listOf(
            UrlTypeName(
                    url = "/api/equipment/$index",
                    type = "equipment",
                    name = name
            )))
