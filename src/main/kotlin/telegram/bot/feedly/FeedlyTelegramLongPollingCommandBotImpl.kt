package telegram.bot.feedly

import model.system.SecretProperties
import model.telegram.MapKeyboardToCommand.Companion.getCommandByKeyboardText
import model.telegram.MapKeyboardToCommand.Companion.isInCommandMap
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update

class FeedlyTelegramLongPollingCommandBotImpl(
    options: DefaultBotOptions?,
    secretConfig: SecretProperties
) : TelegramLongPollingCommandBot(options) {

    private val token = secretConfig.telegramToken
    private val botName = secretConfig.telegramBotName

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = botName

    override fun processNonCommandUpdate(update: Update?) {
        if (isInCommandMap(update!!.message.text)) {
            val command = getCommandByKeyboardText(update.message.text)
            update.message.apply {
                text = "/$command"
                entities = listOf(MessageEntity("bot_command", 0, 6, null, null, null, "/$command"))
            }
            onUpdateReceived(update)
        }
    }
}
