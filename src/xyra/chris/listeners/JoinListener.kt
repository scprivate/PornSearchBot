package xyra.chris.listeners

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import xyra.chris.utilities.sendPM

class JoinListener : ListenerAdapter() {

    override fun onGuildMemberJoin(e : GuildMemberJoinEvent) {

        e.user.sendPM("Hello!\nI'm the search bot.\nI see you've joined one of my servers!\nTo use the bot, type !search in the appropriate channel in the server you joined. It'll give you more info.\n\nGood luck!")

    }
}