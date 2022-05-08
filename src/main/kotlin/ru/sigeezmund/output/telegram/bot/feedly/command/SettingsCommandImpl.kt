package ru.sigeezmund.output.telegram.bot.feedly.command

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.sigeezmund.model.TelegramKeyboardCommands
import ru.sigeezmund.output.telegram.auth.AuthorizationService

class SettingsCommandImpl : BotCommand(TelegramKeyboardCommands.SETTING.commandIdentifier, "Settings") {

    override fun execute(absSender: AbsSender?, user: User?, chat: Chat?, arguments: Array<out String>?) {
        AuthorizationService.checkUser(user!!)
        val message = SendMessage().apply {
            text = "How much news you want by one time?"
            chatId = chat!!.id.toString()
        }
        absSender!!.execute(message)
    }

}