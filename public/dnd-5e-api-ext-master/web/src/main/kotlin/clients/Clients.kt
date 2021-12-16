package clients

import AppResources
import components.characterClass
import components.characterClassLevel
import components.characterFeature
import components.characterProficiency
import dandd.character.automation.models.ability.scores.CharacterAbilityScore
import dandd.character.automation.models.classes.CharacterClass
import dandd.character.automation.models.classes.levels.CharacterClassLevel
import dandd.character.automation.models.equipment.CharacterEquipment
import dandd.character.automation.models.equipment.categories.CharacterEquipmentCategory
import dandd.character.automation.models.features.CharacterFeature
import dandd.character.automation.models.proficiencies.CharacterProficiency
import dandd.character.automation.models.races.CharacterRace
import dandd.character.automation.models.skills.CharacterSkill
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.RBuilder
import web.api.ApiClient
import web.api.ApiClientError
import web.core.Either
import web.core.Left
import web.core.mapLeft
import web.core.mapRight

data class Renderable<T>(val domain: T) {
    fun RBuilder.renderResource(props: AppResources) {
        println("Rendering $domain")
        when(domain) {
            is CharacterAbilityScore -> {}
            is CharacterClass -> characterClass(props) { characterClass = domain }
            is CharacterClassLevel -> characterClassLevel(props) { characterClassLevel = domain }
            is CharacterFeature -> characterFeature(props) { feature = domain }
            is CharacterProficiency -> characterProficiency(props) { characterProficiency = domain }

        }
    }
}

data class LoadsRenderable<T>(val client: ApiClient<T>) {
    suspend fun load(uri: String): Either<RenderLoadingError, Renderable<T>> =
        client.getResourceByUri(uri)
                .mapLeft { ResourceLoadFailure(uri, it) }
                .mapRight { Renderable(it) }
}

class Clients {
    private val client = HttpClient(Js) {}

    val abilityScores = CharacterAbilityScore.client(client)
    val classes = CharacterClass.client(client)
    val classLevels = CharacterClassLevel.client(client)
    val equipment = CharacterEquipment.client(client)
    val equipmentCategories = CharacterEquipmentCategory.client(client)
    val features = CharacterFeature.client(client)
    val proficienies = CharacterProficiency.client(client)
    val races = CharacterRace.client(client)
    val skills = CharacterSkill.client(client)

    private val loadersByName = mapOf(
            CharacterAbilityScore.resourceTypeName to LoadsRenderable(abilityScores),
            CharacterClass.resourceTypeName to LoadsRenderable(classes),
            CharacterClassLevel.resourceTypeName to LoadsRenderable(classLevels),
            CharacterEquipment.resourceTypeName to LoadsRenderable(equipment),
            CharacterEquipmentCategory.resourceTypeName to LoadsRenderable(equipmentCategories),
            CharacterFeature.resourceTypeName to LoadsRenderable(features),
            CharacterProficiency.resourceTypeName to LoadsRenderable(proficienies),
            CharacterRace.resourceTypeName to LoadsRenderable(races),
            CharacterSkill.resourceTypeName to LoadsRenderable(skills)
    )

    suspend fun getRenderable(url: String): Either<RenderLoadingError, Renderable<*>> {
        val parts = url.split("/")
                .map { it.trim() }
                .filter { it != "api" && it.isNotBlank() && it.isNotEmpty() }
        val resource = if (parts.size == 2) {
            val resourceType = parts[0]
            loadersByName.get(resourceType)?.load(url)
        } else if (parts.size == 4) {
            val resourceType = parts[0]
            val subType = parts[2]
            loadersByName.get("$resourceType-$subType")?.load(url)
        } else {
            Left(UnrecognizedUrlLoaderPattern(url))
        }

        return resource?:Left(LoaderNotFound(url))
    }
}

sealed class RenderLoadingError
data class ResourceLoadFailure(val url: String, val error: ApiClientError): RenderLoadingError()
data class UnrecognizedUrlLoaderPattern(val url: String): RenderLoadingError()
data class LoaderNotFound(val url: String): RenderLoadingError()