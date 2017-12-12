package xyra.chris.listeners

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import xyra.chris.commands.ClearSearchesCommand
import xyra.chris.commands.PornhubCommand
import xyra.chris.commands.SearchCommand


class MessageReceivedListener : ListenerAdapter() {

    override fun onMessageReceived(e: MessageReceivedEvent) {
        if (e.message.content.startsWith("!") && !e.member.user.isBot) {

            val content = e.message.content.removePrefix("!")
            val args = content.split(" ")
            val command = args[0]

            fun isCommand(tester: String) = command == tester
            e.message.delete().queue()
            if (args.size == 1) {

                if (isCommand("pornhub")) {
                    PornhubCommand().usage(e, args)
                    return
                }
                if (isCommand("search")) {
                    SearchCommand().usage(e, args)
                }
                if (isCommand("clearsearches")) {
                    ClearSearchesCommand().execute(e, args)
                    return
                }

            } else {

                if (isCommand("pornhub")) {
                    val pornhubCMD = PornhubCommand()
                    pornhubCMD.execute(e, args)
                    return
                }
                
                if (isCommand("search")) {
                    SearchCommand().usage(e, args)
                }
            }
        }
    }
}
