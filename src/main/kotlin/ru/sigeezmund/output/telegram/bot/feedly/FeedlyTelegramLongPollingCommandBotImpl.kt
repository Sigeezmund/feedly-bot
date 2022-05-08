package ru.sigeezmund.output.telegram.bot.feedly

import org.apache.commons.lang3.StringUtils
import ru.sigeezmund.model.SecretProperties
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update
import ru.sigeezmund.model.TelegramKeyboardCommands

class FeedlyTelegramLongPollingCommandBotImpl(
    options: DefaultBotOptions?,
    secretConfig: SecretProperties
) : TelegramLongPollingCommandBot(options) {

    private val token = secretConfig.telegramToken
    private val botName = secretConfig.telegramBotName

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = botName

    override fun processNonCommandUpdate(update: Update?) {
        val commandIdentifier = TelegramKeyboardCommands.getCommandIdentifierByUserCommand(update!!.message.text)
        if (StringUtils.isNotEmpty(commandIdentifier)) {
            update.message.apply {
                text = "/${commandIdentifier}"
                entities = listOf(MessageEntity("bot_command", 0, 6, null, null, null, "/${commandIdentifier}"))
            }
            onUpdateReceived(update)
        }
    }
}
