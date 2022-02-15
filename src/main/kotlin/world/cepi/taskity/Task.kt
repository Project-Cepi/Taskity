package world.cepi.taskity

import java.util.UUID

data class Task(
    val name: String,
    val description: String,
    val note: String?,
    val creator: UUID,
    val claimers: List<UUID>
)