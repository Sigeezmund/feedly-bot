package ru.sigeezmund.output.telegram.handler

import org.reflections.Reflections
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.sigeezmund.model.SecretSingleton
import ru.sigeezmund.output.telegram.bot.feedly.FeedlyTelegramLongPollingCommandBotImpl

class FeedlyBotHandlerImpl : BotHandler {

    private val feedlyBot: TelegramLongPollingCommandBot
    private val telegramBotsApi: TelegramBotsApi

    init {
        feedlyBot = FeedlyTelegramLongPollingCommandBotImpl(DefaultBotOptions(), SecretSingleton.getSecretProperties())
        telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
    }

    override fun startBot() {
        val reflections = Reflections("ru.sigeezmund.telegram")
        val allCommands = reflections.getSubTypesOf(BotCommand::class.java)
        allCommands.forEach {
            feedlyBot.register(it.getDeclaredConstructor().newInstance())
        }
        telegramBotsApi.registerBot(feedlyBot)
    }
}
