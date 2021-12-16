package dand.validation

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import arrow.core.flatMap
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dandd.character.automation.Proficiency
import dandd.character.automation.readResourcesDirectory
import dandd.character.automation.source.ResourceLoaderFactory
import dandd.character.automation.source.Resources
import dandd.character.automation.toMap
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.File
import java.lang.RuntimeException
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.fail


class ProficiencyValidor {

//    @Test
    fun validate_references_source() {
        val urlBase = "http://localhost:8099"
        val mapper = jacksonObjectMapper()
        runBlocking {
            val proficiencies = mapper
                    .readValue<List<Proficiency>>(File("/home/mu-bear/Documents/DandD/automation/proficiencies.json").readText())
            proficiencies.map { proficiency -> proficiency.references?.map {
                assert(it.url.startsWith("/api/${it.type}/"))
                val response = khttp.get("$urlBase${it.url}")
                assertEquals(response.statusCode, 200, "Path ${it.url} was unsuccessful")
                assertNotNull(response.jsonObject)
            } }
        }
    }
//    @Test
    fun validate_references_api() {
//        assert(true)
//        val urlBase = "http://localhost:8099"
//        val factory = ResourceLoaderFactory(urlBase, readResourcesDirectory())
//        val mapper = ObjectMapper()
//        val resources = Resources(mapper).dndResources(factory)
//        val proficienciesLoader = resources.find { it.resourceNames == listOf("proficiencies") }
//        assertNotNull(proficienciesLoader, "Could not find proficiencies loader")
//        runBlocking {
//            val loader = proficienciesLoader.resourceLoader
//            val resources = loader.loadAll()
//            resources.map {
//                val resource = when(it) {
//                    is Either.Right -> it.b
//                    is Either.Left -> fail("could not fetch resource ${it.a}")
//                }
//
//                val proficiency = resource.toMap(mapper)
//                val resources = proficiency.get("resources")
//                val references = when(resources) {
//                    null -> fail("resources field is null")
//                    is Array<*> -> resources
//                    else -> fail("Unexpected value for resources: $it")
//                }
//                references
//                        .map { when(it) {
//                            null -> fail("resource item is null")
//                            is Map<*, *> -> it
//                            else -> fail("Unexpected value for resource item: $it")
//                        } }
//                        .map {
//                    val name = it.get("name")
//                    when (name) {
//                        null -> fail("reference had no name")
//                        is String -> {}
//                        else -> fail("Unexpected resource name $name")
//                    }
//
//                    val type = it.get("type")
//                    when(type) {
//                        null -> fail("reference had no type")
//                        is String -> {}
//                        else -> fail("Unexpected resource type $type")
//                    }
//
//                    val url = it.get("url")
//                    when(url) {
//                        null -> fail("reference had no url")
//                        is String -> {}
//                        else -> fail("Unexpected resource url $type")
//                    }
//
//                    val response = khttp.get("$urlBase$url")
//                    assertEquals(response.statusCode, 200)
//                    assertNotNull(response.jsonObject)
//                }
//
//            }
//
//        }



    }

}
