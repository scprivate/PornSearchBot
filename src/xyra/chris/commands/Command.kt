package xyra.chris.commands

import net.dv8tion.jda.core.events.message.MessageReceivedEvent

interface Command {

    fun usage(e: MessageReceivedEvent, args : List<String>)
    fun execute(e: MessageReceivedEvent, args : List<String>)



}