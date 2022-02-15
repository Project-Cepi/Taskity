package world.cepi.taskity

import net.minestom.server.extensions.Extension;

class Taskity : Extension() {

    companion object {
        val categories: MutableList<Category> = mutableListOf()
    }

    override fun initialize(): LoadStatus {

        TaskCommand.register()
        TaskityCommand.register()

        logger().info("[Taskity] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {

        TaskCommand.unregister()
        TaskityCommand.unregister()

        logger().info("[Taskity] has been disabled!")
    }

}
