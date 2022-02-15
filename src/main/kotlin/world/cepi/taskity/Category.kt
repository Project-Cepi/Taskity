package world.cepi.taskity

data class Category(
    val name: String,
    val tasks: List<Task> = listOf()
)