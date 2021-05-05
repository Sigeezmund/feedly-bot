package model.telegram

data class NewsTelegramDto(
    val sourceName: String,
    val newsTitle: String,
    val href: String,
    val content: String?
) {
    fun prettyString(): String {
        return "**Source** : $sourceName \n\n\n$newsTitle\n\n\n**Link**: $href"
    }
}
