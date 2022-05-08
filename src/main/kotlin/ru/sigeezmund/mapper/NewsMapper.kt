package ru.sigeezmund.mapper

import ru.sigeezmund.model.FeedlyNewsItem
import ru.sigeezmund.model.TelegramNewsDto

class NewsMapper {

    fun getTelegramNewsDto(newsItems: List<FeedlyNewsItem>): List<TelegramNewsDto> =
        newsItems.map { newsItem ->
            TelegramNewsDto(
                newsTitle = newsItem.title,
                sourceName = newsItem.origin.title,
                href = newsItem.canonicalUrl ?: newsItem.ampUrl ?: newsItem.alternate[0].href,
                content = newsItem.summary.content
            )
        }.toList()
}
