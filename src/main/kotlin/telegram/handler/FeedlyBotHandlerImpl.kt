package telegram.handler

import model.system.SecretSingleton
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import telegram.bot.feedly.FeedlyTelegramLongPollingCommandBotImpl
import telegram.bot.feedly.comand.FreshNewsBotCommandImpl
import telegram.bot.feedly.comand.StartFeedlyCommandImpl

class FeedlyBotHandlerImpl : BotHandler {

    private val feedlyBot: TelegramLongPollingCommandBot
    private val telegramBotsApi: TelegramBotsApi
    private val secret = SecretSingleton

    init {
        feedlyBot = FeedlyTelegramLongPollingCommandBotImpl(DefaultBotOptions(), secret.getSecretProperties())
        telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
    }

    override fun startBot() {
        feedlyBot.register(FreshNewsBotCommandImpl(secret))
        feedlyBot.register(StartFeedlyCommandImpl())
        telegramBotsApi.registerBot(feedlyBot)
    }
}
