package model.system

import java.io.FileInputStream
import java.util.Properties

object SecretSingleton {

    private var secretProperties: SecretProperties

    init {
        val property = Properties()
        property.load(FileInputStream("src/main/resources/config.properties"))
        secretProperties = SecretProperties(
            telegramToken = property.getProperty("telegram.token"),
            telegramBotName = property.getProperty("telegram.botName"),
            feedlyAuthToken = property.getProperty("feedly.auth"),
            feedlyUserId = property.getProperty("feedly.user")
        )
    }

    fun getSecretProperties(): SecretProperties = secretProperties
}
