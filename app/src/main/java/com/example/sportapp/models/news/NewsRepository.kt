package com.example.sportapp.models.news

import android.util.Log
import com.example.sportapp.models.news.api.NewsResponse
import com.example.sportapp.models.news.domain.NewsEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class NewsRepository {

    private val newsBaseUrl = "https://d9b9-51-159-14-212.ngrok-free.app"
    private val controllerPath = "NewsController"

    val newsMapper = NewsMapper()

    private val json = Json {
        this.ignoreUnknownKeys = true
    }

    private val client = HttpClient {
        defaultRequest {
            this.url(newsBaseUrl)
        }

        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                val contentType = response.headers[HttpHeaders.ContentType]
                if (contentType != null) {

                    val charset = contentType.split("charset=").getOrNull(1) ?: "Unknown"
                    Log.d("tttNews", "Response Charset: $charset")
                } else {
                    Log.d("tttNews", "Content-Type header not found")
                }
            }
        }
    }

    suspend fun getNews(): List<NewsEntity> {
        val builder = HttpRequestBuilder()

        builder.method = HttpMethod.Get

        builder.url {
            (newsBaseUrl)
            encodedPath = "/$controllerPath"
        }

        val requestUrl = builder.url.toString()
        Log.d("tttNews", "Request URL: $requestUrl")

        val response = client.request(builder)

        Log.d("tttNews", response.toString())

        val responseString: String = response.body()

        Log.d("tttNews", responseString)

        val newsResponse: List<NewsResponse> = json.decodeFromString(responseString)

        return newsMapper.getNewsEntityList(newsResponse)
    }
}






