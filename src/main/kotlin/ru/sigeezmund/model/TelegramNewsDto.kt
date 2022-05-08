package ru.sigeezmund.model

data class TelegramNewsDto(
    val sourceName: String,
    val newsTitle: String,
    val href: String,
    val content: String?
) {
    fun prettyString(): String {
        return "Source : $sourceName \n\n\n$newsTitle\n\n\nLink: $href"
    }
}
