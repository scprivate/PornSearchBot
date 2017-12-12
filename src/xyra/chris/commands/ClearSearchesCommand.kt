package xyra.chris.commands

import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class ClearSearchesCommand : Command {
    override fun usage(e: MessageReceivedEvent, args: List<String>) {

    }

    override fun execute(e: MessageReceivedEvent, args: List<String>) {
        e.textChannel.delete().queue()
        e.guild.controller.createTextChannel("bot").queue()
    }
}