package world.cepi.taskity

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.arguments.suggest
import world.cepi.kstom.command.kommand.Kommand

object TaskityCommand : Kommand({
    val category by literal
    val task by literal
    val add by literal
    val remove by literal
    val list by literal

    val newCategoryName = ArgumentType.String("newCategoryName").filter { name ->
        Taskity.categories.none { it.name == name }
    }

    val categoryIcon = ArgumentType.ItemStack("categoryIcon")

    val existingCategory = ArgumentType.String("existingCategoryName").map { name ->
        return@map Taskity.categories.firstOrNull { it.name == name } ?: throw ArgumentSyntaxException("No category found", name, 1)
    }.suggest { Taskity.categories.map { it.name } }

    val newTaskName = ArgumentType.String("newTaskName").filter { name ->
        Taskity.categories.none { it.tasks.none { task -> task.name == name }  }
    }

    val existingTask = ArgumentType.String("existingTask").map { name ->
        return@map Taskity.categories.firstNotNullOfOrNull { it.tasks.firstOrNull { task -> task.name == name } } ?: throw ArgumentSyntaxException("No category found", name, 1)
    }.suggest { Taskity.categories.map { it.name } }

    syntax(category, add, newCategoryName) {

        Taskity.categories.add(Category(!newCategoryName))

        sender.sendFormattedTranslatableMessage(
            "task", "category.add",
            Component.text(!newCategoryName, NamedTextColor.BLUE)
        )

    }

    syntax(category, add, newCategoryName, categoryIcon) {

        Taskity.categories.add(Category(!newCategoryName, (!categoryIcon).material))

        sender.sendFormattedTranslatableMessage(
            "task", "category.add",
            Component.text(!newCategoryName, NamedTextColor.BLUE)
        )

    }

    syntax(category, remove, existingCategory) {

        Taskity.categories.remove(!existingCategory)

        sender.sendFormattedTranslatableMessage(
            "task", "category.remove",
            Component.text((!existingCategory).name, NamedTextColor.BLUE)
        )
    }

    syntax(category, list) {

        Taskity.categories.forEach {
            player.sendFormattedTranslatableMessage(
                "task", "category.list",
                Component.text(it.name, NamedTextColor.BLUE),
                Component.text(it.tasks.size, NamedTextColor.DARK_GRAY)
            )
        }

    }

    syntax(category, list, existingCategory) {

    }

    syntax(task, add, existingCategory, newTaskName) {

    }

    syntax(task, remove, existingCategory, existingTask) {

    }

}, "taskity")