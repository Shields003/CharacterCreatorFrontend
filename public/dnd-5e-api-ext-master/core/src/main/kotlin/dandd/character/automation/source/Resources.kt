package dandd.character.automation.source

import arrow.core.Either
import com.fasterxml.jackson.databind.ObjectMapper
import dandd.character.automation.Result
import dandd.character.automation.toMap

class Resources(
        val objectMapper: ObjectMapper
) {

    val readIndexField: suspend (String) -> Result<String> =
            { json: String ->
                Either.catch {
                    val index = json.toMap(objectMapper).get("index")
                    when (index) {
                        null -> throw java.lang.RuntimeException(" No INdex for $json")
                        else -> index.toString()
                    }
                }
            }

//    fun <T> of(body: ResourceLoaderFactory.() -> List<ResourceConfig<T>>): List<ResourceConfig<T>> = body()

    fun <T, ID: Any> createResourceConfig(
            name: List<String>,
            className: String,
           resourceLoader: ResourceLoader<T, ID>
    ) = ResourceConfig(name, className, resourceLoader)

    fun dndResources(factory: ResourceLoaderFactory) = factory.dndResources()
    fun dndNestedResources(factory: ResourceLoaderFactory) = factory.dndNestedResources()

    val dndResources: ResourceLoaderFactory.() -> List<ResourceConfig<String, String>> = {
        val resource = { name: String, className: String ->
            createResourceConfig(listOf(name), className,
                    createLoaderFor(name,
                            { Either.Right(it) },
                            { Either.Right(it) },
                            readIndexField))
        }
        listOf(
            resource("ability-scores", "CharacterAbilityScore"),
            resource("classes" ,  "CharacterClass"),
            resource("conditions" ,  "CharacterCondtion"),
            resource("damage-types" ,  "CharacterDamageType"),
            resource("equipment-categories" ,  "CharacterEquipmentCategory"),
            resource("equipment" ,  "CharacterEquipment"),
            resource("features" ,  "CharacterFeature"),
            resource("languages" ,  "CharacterLanguage"),
            resource("magic-schools" ,  "CharacterMagicSchool"),
            resource("proficiencies" ,  "CharacterProficiency"),
            resource("races" ,  "CharacterRace"),
            resource("skills" ,  "CharacterSkill"),
            resource("spellcasting" ,  "CharacterSpellcasting"),
            resource("spells" ,  "CharacterSpell"),
            resource("starting-equipment" ,  "CharacterStartingEquipment"),
            resource("subclasses" ,  "CharacterSubclass"),
            resource("subraces" ,  "CharacterSubrace"),
            resource("traits" ,  "CharacterTrait"),
            resource("weapon-properties" ,  "CharacterWeaponPropert"))
    }

    val dndNestedResources: ResourceLoaderFactory.() -> List<ResourceConfig<String, Pair<String, String>>> = {
        val config =
                ResourceConfig(
                        listOf("classes", "levels"),
                        "CharacterClassLevel",
                        createNestedLoaderFor("classes",
                                "levels",
                                { Either.Right(it) },
                                { Either.Right(it) },
                                { json: String ->
                                    Either.catch {
                                        val map = json.toMap(objectMapper)
                                        val level = map.get("level")

                                        val levelIdx = when (level) {
                                            null -> throw java.lang.RuntimeException(" No level for $json")
                                            else -> level.toString()
                                        }

                                        val `class` = map.get("class")
                                        val url = when(`class`) {
                                            is Map<*, *> -> `class`.get("url")
                                            else ->throw java.lang.RuntimeException("class was not a map, but was $`class`")
                                        }

                                        val classIdx = when(url) {
                                            is String -> url.split("/").lastOrNull()
                                            else -> null
                                        }?: throw java.lang.RuntimeException("no classIdx in $`class`")

                                        classIdx to levelIdx
                                    }
                                }))



        listOf(config)
    }


//    fun dAndDResources() = of {
//        listOf(
//                resource("ability-scores", "CharacterAbilityScore"),
//                resource("classes" ,  "CharacterClass"),
//                resource("conditions" ,  "CharacterCondtion"),
//                resource("damage-types" ,  "CharacterDamageType"),
//                resource("equipment-categories" ,  "CharacterEquipmentCategory"),
//                resource("equipment" ,  "CharacterEquipment"),
//                resource("features" ,  "CharacterFeature"),
//                resource("languages" ,  "CharacterLanguage"),
//                resource("magic-schools" ,  "CharacterMagicSchool"),
//                resource("proficiencies" ,  "CharacterProficiency"),
//                resource("races" ,  "CharacterRace"),
//                resource("skills" ,  "CharacterSkill"),
//                resource("spellcasting" ,  "CharacterSpellcasting"),
//                resource("spells" ,  "CharacterSpell"),
//                resource("starting-equipment" ,  "CharacterStartingEquipment"),
//                resource("subclasses" ,  "CharacterSubclass"),
//                resource("subraces" ,  "CharacterSubrace"),
//                resource("traits" ,  "CharacterTrait"),
//                resource("weapon-properties" ,  "CharacterWeaponPropert"))
//    }
}