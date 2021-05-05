package model.telegram

import model.telegram.CommandConst.Companion.FRESH_BUTTON_KEYBOARD
import model.telegram.CommandConst.Companion.FRESH_COMMAND

class MapKeyboardToCommand {
    companion object {
        private val commandMap: Map<String, String> = hashMapOf(
            FRESH_BUTTON_KEYBOARD to FRESH_COMMAND
        )

        fun inCommandMap(keyboardCommand: String) = commandMap.containsKey(keyboardCommand)

        fun getCommandByKeyboardText(keyboardCommand: String) = commandMap[keyboardCommand]
    }
}

class CommandConst {
    companion object {
        const val FRESH_COMMAND = "fresh"
        const val FRESH_BUTTON_KEYBOARD = "Give Some Fresh News"
    }
}
