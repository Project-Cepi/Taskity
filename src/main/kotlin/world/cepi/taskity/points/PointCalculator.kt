package world.cepi.taskity.points

object PointCalculator {

    fun generate(priority: Priority, size: Size, skill: Skill): Int {
        return priority.value * ((10 * size.value) + (7 * skill.value))
    }

}