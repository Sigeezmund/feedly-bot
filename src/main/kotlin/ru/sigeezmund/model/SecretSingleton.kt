package ru.sigeezmund.model

object SecretSingleton {

    private var secretProperties: SecretProperties

    init {
        secretProperties = SecretProperties(
            telegramToken = System.getenv("telegram.token"),
            telegramBotName = System.getenv("telegram.botName"),
            feedlyAuthToken = System.getenv("feedly.auth"),
            feedlyUserId = System.getenv("feedly.user")
        )
    }

    fun getSecretProperties(): SecretProperties = secretProperties
}
