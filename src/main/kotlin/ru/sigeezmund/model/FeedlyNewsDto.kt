package ru.sigeezmund.model

data class FeedlyNewsDto(
    val id: String,
    val items: List<FeedlyNewsItem>
)

data class FeedlyNewsItem(
    val alternate: List<Alternate>,
    val ampUrl: String,
    val canonicalUrl: String,
    val categories: List<Category>,
    val cdnAmpUrl: String,
    val crawled: Long,
    val enclosure: List<Enclosure>,
    val engagement: Int,
    val engagementRate: Double,
    val fingerprint: String,
    val id: String,
    val origin: Origin,
    val originId: String,
    val published: Long,
    val summary: Summary,
    val title: String,
    val unread: Boolean,
    val visual: Visual
)

data class Alternate(
    val href: String,
    val type: String
)

data class Category(
    val id: String,
    val label: String
)

data class Enclosure(
    val href: String,
    val length: Int,
    val type: String
)

data class Origin(
    val htmlUrl: String,
    val streamId: String,
    val title: String
)

data class Summary(
    val content: String,
    val direction: String
)

data class Visual(
    val contentType: String,
    val edgeCacheUrl: String,
    val expirationDate: Long,
    val height: Int,
    val processor: String,
    val url: String,
    val width: Int
)
