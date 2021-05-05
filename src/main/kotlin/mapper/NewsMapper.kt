package mapper

import model.feedly.NewsItem
import model.telegram.NewsTelegramDto

class NewsMapper {

    fun getTelegramNewsDto(newsItems: List<NewsItem>): List<NewsTelegramDto> =
        newsItems.map { newsItem ->
            NewsTelegramDto(
                newsTitle = newsItem.title,
                sourceName = newsItem.origin.title,
                href = newsItem.canonicalUrl ?: newsItem.ampUrl ?: newsItem.alternate[0].href,
                content = newsItem.summary.content
            )
        }.toList()
}
