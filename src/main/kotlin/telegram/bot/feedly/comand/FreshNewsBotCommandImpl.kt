package telegram.bot.feedly.comand

import feedly.FeedlyClient
import kotlinx.coroutines.runBlocking
import mapper.NewsMapper
import model.system.SecretSingleton
import model.telegram.CommandConst.Companion.FRESH_COMMAND
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import telegram.auth.AuthorizationService.Companion.checkUser

class FreshNewsBotCommandImpl(secret: SecretSingleton) : BotCommand(FRESH_COMMAND, "Get Some Fresh News") {

    private val feedlyClient = FeedlyClient(secret.getSecretProperties())

    override fun execute(absSender: AbsSender?, user: User?, chat: Chat?, arguments: Array<out String>?) {
        checkUser(user!!)
        val newsItems = runBlocking {
            feedlyClient.getTopNewsByDay()
        }.items

        val telegramNewsItems = NewsMapper().getTelegramNewsDto(newsItems)
        telegramNewsItems.forEach {
            val message = SendMessage().apply {
                text = it.prettyString()
                chatId = chat!!.id.toString()
            }
            absSender!!.execute(message)
        }

        runBlocking {
            feedlyClient.markNewsRead(newsItems)
        }
    }
}
