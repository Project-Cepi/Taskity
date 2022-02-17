package world.cepi.taskity

import net.minestom.server.item.Material

data class Category(
    val name: String,
    val icon: Material = Material.CHEST,
    val tasks: List<Task> = listOf()
)