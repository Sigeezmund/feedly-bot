package ru.sigeezmund.output.telegram.bot.feedly.command

import ru.sigeezmund.output.feedly.FeedlyClient
import kotlinx.coroutines.runBlocking
import ru.sigeezmund.mapper.NewsMapper
import ru.sigeezmund.model.SecretSingleton
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.sigeezmund.model.TelegramKeyboardCommands
import ru.sigeezmund.output.telegram.auth.AuthorizationService.Companion.checkUser

class FreshNewsBotCommandImpl() : BotCommand(TelegramKeyboardCommands.FRESH.commandIdentifier, "Get Some Fresh News") {

    private val feedlyClient = FeedlyClient(SecretSingleton.getSecretProperties())

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
