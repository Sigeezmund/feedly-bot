package ru.sigeezmund.model

enum class TelegramKeyboardCommands(val commandIdentifier: String, val userCommandName: String, val isShowed: Boolean) {
    START("start", "Start", false),
    FRESH("fresh", "Give Some Fresh News", true),
    SETTING("settings", "Settings", true);

    companion object {
        fun getCommandIdentifierByUserCommand(userCommandName: String): String? =
            enumValues<TelegramKeyboardCommands>().find { it.userCommandName == userCommandName }?.commandIdentifier
    }
}