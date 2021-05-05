package telegram.bot.feedly

import model.system.SecretProperties
import model.telegram.CommandConst.Companion.FRESH_BUTTON_KEYBOARD
import model.telegram.MapKeyboardToCommand.Companion.getCommandByKeyboardText
import model.telegram.MapKeyboardToCommand.Companion.inCommandMap
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

class FeedlyTelegramLongPollingCommandBotImpl(
    options: DefaultBotOptions?,
    secretConfig: SecretProperties
) : TelegramLongPollingCommandBot(options) {

    private val token = secretConfig.telegramToken
    private val botName = secretConfig.telegramBotName

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = botName

    override fun onUpdatesReceived(updates: MutableList<Update>?) {
        val keyBoard = ReplyKeyboardMarkup()
        keyBoard.keyboard = listOf(
            KeyboardRow().apply {
                add(KeyboardButton(FRESH_BUTTON_KEYBOARD))
            }
        )
        if (!updates.isNullOrEmpty()) {
            for (update in updates) {
                execute(SendMessage().apply {
                    replyMarkup = keyBoard
                    text = "Hi"
                    chatId = update.message.chatId.toString()
                })
            }
        }
        super.onUpdatesReceived(updates)
    }

    override fun processNonCommandUpdate(update: Update?) {
        if (inCommandMap(update!!.message.text)) {
            val command = getCommandByKeyboardText(update.message.text)
            update.message.apply {
                text = "/$command"
                entities = listOf(MessageEntity("bot_command", 0, 6, null, null, null, "/$command"))
            }
            onUpdateReceived(update)
        }
    }
}
