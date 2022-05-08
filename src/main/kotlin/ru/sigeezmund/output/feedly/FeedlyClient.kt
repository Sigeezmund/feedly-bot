package ru.sigeezmund.output.feedly

import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.HttpHeaders.Authorization
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import ru.sigeezmund.model.FeedlyNewsDto
import ru.sigeezmund.model.FeedlyNewsItem
import ru.sigeezmund.model.feedly.MarkArticleOnFeedlyDto
import ru.sigeezmund.model.SecretProperties

class FeedlyClient(secretProperties: SecretProperties) {

    private val feedlyAccessToken = secretProperties.feedlyAuthToken
    private val userId = secretProperties.feedlyUserId
    private val gson = Gson()
    private val client = HttpClient(CIO) {
        expectSuccess = false
    }

    suspend fun getTopNewsByDay(): FeedlyNewsDto {
        val response: HttpResponse =
            client.request("$FEEDLY_URL/$FEEDLY_VERSION/mixes/user%2F$userId%2Fcategory%2Fglobal.all/contents") {
                method = HttpMethod.Get
                headers.append(Authorization, "Bearer $feedlyAccessToken")
                parameter("boostMustRead", "true")
                parameter("hours", "10")
                parameter("count", "10")
                parameter("unreadOnly", "true")
                parameter("backfill", "true")
            }
        if(response.status == HttpStatusCode.OK) {
            return gson.fromJson(response.readText(), FeedlyNewsDto::class.java)
        } else {
            error("Have response wrong answer [{${response.status}}]")
        }

    }

    suspend fun markNewsRead(newsItem: List<FeedlyNewsItem>) {
        val entityId = mutableListOf<String>()
        for (news in newsItem) {
            entityId.add(news.id)
        }
        val response: HttpResponse =
            client.request("$FEEDLY_URL/$FEEDLY_VERSION/markers") {
                method = HttpMethod.Post
                headers.append(Authorization, "Bearer $feedlyAccessToken")
                body = gson.toJson(MarkArticleOnFeedlyDto(entityId))
            }
        if (response.status != HttpStatusCode.OK) {
            print("Cannot mark news [$entityId] like read")
        }
    }

    private companion object {
        const val FEEDLY_URL = "https://cloud.feedly.com"
        const val FEEDLY_VERSION = "v3"
    }
}
