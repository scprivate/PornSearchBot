package xyra.chris.bot


import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import xyra.chris.listeners.JoinListener
import xyra.chris.listeners.MessageReceivedListener

object Main {

    var discord: JDA? = null

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            discord = JDABuilder(AccountType.BOT).setToken(Constants.TOKEN).buildBlocking()
            discord!!.isAutoReconnect = true

        } catch (e: Exception) {
            e.printStackTrace()
        }

        discord!!.addEventListener(MessageReceivedListener())
        discord!!.addEventListener(JoinListener())
    }
}