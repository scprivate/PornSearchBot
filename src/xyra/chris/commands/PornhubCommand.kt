package xyra.chris.commands

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import org.jsoup.Jsoup
import xyra.chris.utilities.sendPM
import xyra.chris.utilities.toChannel

open class PornhubCommand : Command {

    override fun usage(e: MessageReceivedEvent, args: List<String>) {
        e.message.delete()
        "${e.author.asMention}\nUSAGE: !pornhub #{FLAG} \${SEARCH TYPE} <SEARCH>\n\n:white_check_mark: FLAGS :white_check_mark:\nprivate\npublic\n\n:ballot_box_with_check: SEARCH TYPES :ballot_box_with_check: \nvideos\nimages\ntitles\nwebm (preview of video link)\n\nEXAMPLE: !pornhub #public \$videos ass".toChannel(e.textChannel)
    }

    override fun execute(e: MessageReceivedEvent, args: List<String>) {

        if (!args[1].startsWith("#") || !args[2].startsWith("$")) {
            "Please use proper tags!\nPlease use proper search types!".toChannel(e.textChannel)
            return
        }

        val builtString = StringBuilder()
        args.forEach { if (!it.startsWith("#") && (!it.startsWith("$"))) builtString.append(it).append("+") }
        val search = builtString.toString().removeSuffix("+").removePrefix("pornhub")
        val website = Jsoup.connect("https://www.pornhub.com/video/search?search=$search").get().select("a[href]")

        val searchType = args[2].removePrefix("$")
        val privacyType = args[1].removePrefix("#")

        var runs = 0



        if(privacyType != "public") {
            e.author.sendPM("We've made it private by default, if you put in something other than private or public\nfor your safety. :wink:")
        } else {
            "Searching PornHub for $searchType relating to your search, '${search.replace('+',' ')}',  ${e.author.asMention}..".toChannel(e.textChannel)
        }

        //Loop through all the elements in the href\\
        kotlin.concurrent.thread {
            for (href in website) {
                if (runs < 5) {
                    if (href.hasAttr("data-related-url") && href.attr("data-related-url").contains("/video/ajax_related_video?vkey=") && href.getElementsByAttribute("src").attr("data-mediumthumb").isNotBlank() && href.getElementsByAttribute("src").attr("data-mediabook").isNotBlank()) {

                        val link: String = when (searchType) {
                            "videos" -> "https://www.pornhub.com${href.attr("href")}"
                            "images" -> href.getElementsByAttribute("src").attr("data-mediumthumb")
                            "titles" -> href.attr("title")
                            "webm" -> href.getElementsByAttribute("src").attr("data-mediabook")
                            else -> {
                                if(privacyType != "private") {
                                    "Invalid search type, ${e.author.asMention} \n\n :white_check_mark: FLAGS :white_check_mark:\nprivate\npublic\n\n:ballot_box_with_check: SEARCH TYPES :ballot_box_with_check: \nvideos\nimages\ntitles\nwebm (preview of video link)".toChannel(e.textChannel)
                                }
                                return@thread
                            }
                        }
                        when (privacyType) {
                            "public" -> link.toChannel(e.textChannel)
                            "private" -> e.member.user.sendPM(link)
                            else -> {e.member.user.sendPM(link)}
                        }
                        Thread.sleep(1500)
                        runs++
                    }
                } else {
                    return@thread
                }
            }
        }
    }
}