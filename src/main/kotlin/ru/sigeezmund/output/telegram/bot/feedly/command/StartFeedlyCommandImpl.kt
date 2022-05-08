package ru.sigeezmund.output.telegram.bot.feedly.command

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.sigeezmund.model.TelegramKeyboardCommands

class StartFeedlyCommandImpl : BotCommand(TelegramKeyboardCommands.START.commandIdentifier, "start") {

    override fun execute(absSender: AbsSender?, user: User?, chat: Chat?, arguments: Array<out String>?) {
        val keyBoard = ReplyKeyboardMarkup()
        with(keyBoard) {
            resizeKeyboard = true
            keyboard = listOf(generateKeyboardRow())
        }
        absSender!!.execute(SendMessage().apply {
            replyMarkup = keyBoard
            text = "So, after that, you have unlimited access to fresh news by tap"
            chatId = chat!!.id.toString()
        })
    }

    private fun generateKeyboardRow(): KeyboardRow {
        val keyboardRow = KeyboardRow()
        TelegramKeyboardCommands.values().forEach {
            if (it.isShowed) {
                keyboardRow.apply { add(KeyboardButton(it.userCommandName)) }
            }
        }
        return keyboardRow
    }
}
