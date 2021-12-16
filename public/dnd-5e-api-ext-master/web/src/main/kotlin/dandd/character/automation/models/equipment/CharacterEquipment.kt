
package dandd.character.automation.models.equipment

import io.ktor.client.HttpClient
import web.api.ApiClient
import web.api.ClientParseError
import web.core.Either
import web.core.bindRight
import web.core.mapLeft
import web.parse.*
import kotlin.js.Json

data class CharacterEquipment(
    val _id: String,
    val index: String,
    val name: String,
    val equipment_category: NameUrl,
    val gear_category: String?,
    val cost: Cost,
    val weight: Double?,
    val url: String,
    val weapon_category: String?,
    val weapon_range: String?,
    val category_range: String?,
    val damage: DamageDiceDamageType?,
    val range: Range?,
    val properties: List<NameUrl>?,
    val tool_category: String?,
    val desc: List<String>?,
    val vehicle_category: String?,
    val speed: Cost?,
    val _2h_damage: DamageDiceDamageType?,
    val contents: List<Contents>?,
    val capacity: String?,
    val armor_category: String?,
    val armor_class: ArmorClass?,
    val str_minimum: Int?,
    val stealth_disadvantage: Boolean?,
    val quantity: Int?,
    val throw_range: ThrowRange?,
    val special: List<String>?
) {
    companion object {
        val resourceTypeName = "equipment"
        val parseResponseBody = { jsonString: String -> Either
            .catching { JSON.parse<Json>(jsonString) }
            .mapLeft { JsonParse(it) }
            .bindRight { from(it) }
            .mapLeft { ClientParseError(jsonString, it) }
        }
        
        fun from(json: Json?): Either<ParseError, CharacterEquipment> =
            json.parse {
                obj {
                    CharacterEquipment(
                        "_id".str(),
                        "index".str(),
                        "name".str(),
                        "equipment_category".obj {
                            NameUrl.from(node).bind()
                        },
                        "gear_category".nullable {
                            str()
                        },
                        "cost".obj {
                            Cost.from(node).bind()
                        },
                        "weight".nullable {
                            double()
                        },
                        "url".str(),
                        "weapon_category".nullable {
                            str()
                        },
                        "weapon_range".nullable {
                            str()
                        },
                        "category_range".nullable {
                            str()
                        },
                        "damage".nullable {
                            obj {
                                DamageDiceDamageType.from(node).bind()
                            }
                        },
                        "range".nullable {
                            obj {
                                Range.from(node).bind()
                            }
                        },
                        "properties".nullable {
                            arr {
                                obj {
                                    NameUrl.from(node).bind()
                                }
                            }
                        },
                        "tool_category".nullable {
                            str()
                        },
                        "desc".nullable {
                            arr {
                                str()
                            }
                        },
                        "vehicle_category".nullable {
                            str()
                        },
                        "speed".nullable {
                            obj {
                                Cost.from(node).bind()
                            }
                        },
                        "2h_damage".nullable {
                            obj {
                                DamageDiceDamageType.from(node).bind()
                            }
                        },
                        "contents".nullable {
                            arr {
                                obj {
                                    Contents.from(node).bind()
                                }
                            }
                        },
                        "capacity".nullable {
                            str()
                        },
                        "armor_category".nullable {
                            str()
                        },
                        "armor_class".nullable {
                            obj {
                                ArmorClass.from(node).bind()
                            }
                        },
                        "str_minimum".nullable {
                            int()
                        },
                        "stealth_disadvantage".nullable {
                            boolean()
                        },
                        "quantity".nullable {
                            int()
                        },
                        "throw_range".nullable {
                            obj {
                                ThrowRange.from(node).bind()
                            }
                        },
                        "special".nullable {
                            arr {
                                str()
                            }
                        }
                    )
                }
            }
        
    fun client(httpClient: HttpClient): Client =
            Client(httpClient)
            
    class Client(override val httpClient: HttpClient): ApiClient<CharacterEquipment> {
        override val parse = parseResponseBody
        
        suspend fun getCharacterEquipment(equipment: String) = 
            getResourceByUri("/api/equipment/${equipment}")
    }

    }
}
