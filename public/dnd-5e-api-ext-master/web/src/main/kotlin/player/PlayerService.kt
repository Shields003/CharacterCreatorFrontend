package player

interface PlayerService {
    suspend fun getCharacters(): List<PlayerCharacter>
}

class StaticPlayerService: PlayerService {
    override suspend fun getCharacters(): List<PlayerCharacter> {
        return listOf(PlayerCharacter(30, 32, "half-elf", mapOf("sorcerer" to 5)))
    }
}

