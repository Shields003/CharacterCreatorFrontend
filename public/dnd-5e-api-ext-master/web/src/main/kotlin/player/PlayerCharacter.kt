package player

data class PlayerCharacter(
        val currentHitPoints: Int,
        val maxHitPoints: Int,
        val race: String,
        val classLevels: Map<String, Int>
)