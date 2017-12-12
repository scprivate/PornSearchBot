package xyra.chris.commands

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import xyra.chris.utilities.toChannel

class SearchCommand : Command {
    override fun usage(e: MessageReceivedEvent, args: List<String>) {
        "Welcome to Search bot!\n\nDifferent types of searches are:\n\n!pornhub\n!google\n\nType either of those to get their specific usages!\n\nEach is different. Enjoy! :wink:\n\nOther commands are:\n\n!clearsearches".toChannel(e.textChannel)
    }

    override fun execute(e: MessageReceivedEvent, args: List<String>) {

    }
}