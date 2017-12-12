package xyra.chris.utilities

import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.entities.User

fun User.sendPM(message: String) = this.openPrivateChannel().queue { it.sendMessage(message).queue() }
fun String.toChannel(channel: TextChannel) = channel.sendMessage(this).queue()
