package ru.sigeezmund.output.telegram.auth

import org.telegram.telegrambots.meta.api.objects.User

class AuthorizationService {
    companion object {

        private val listOfAvailableUser = listOf<String>()

        fun checkUser(user: User?) {
            if ((user == null) && (listOfAvailableUser.contains(user?.userName))) {
                throw RuntimeException("Unknown User")
            }
        }
    }
}
