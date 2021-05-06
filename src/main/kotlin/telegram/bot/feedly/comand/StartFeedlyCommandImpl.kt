package telegram.bot.feedly.comand

import model.telegram.CommandConst
import model.telegram.CommandConst.Companion.START_COMMAND
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.bots.AbsSender

class StartFeedlyCommandImpl : BotCommand(START_COMMAND, "start") {

    override fun execute(absSender: AbsSender?, user: User?, chat: Chat?, arguments: Array<out String>?) {
        val keyBoard = ReplyKeyboardMarkup()
        with(keyBoard) {
            resizeKeyboard = true
            keyboard = listOf(
                KeyboardRow().apply {
                    add(KeyboardButton(CommandConst.FRESH_BUTTON_KEYBOARD))
                }
            )
        }
        absSender!!.execute(SendMessage().apply {
            replyMarkup = keyBoard
            text = "So, after that, you have unlimited access to fresh news by tap"
            chatId = chat!!.id.toString()
        })
    }
}
