package ru.sigeezmund.model

data class SecretProperties(
    val telegramToken: String,
    val telegramBotName: String,
    val feedlyAuthToken: String,
    val feedlyUserId: String
)
