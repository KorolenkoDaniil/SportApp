package com.example.sportapp.models.news

import android.util.Log
import com.example.sportapp.models.news.api.NewsResponse
import com.example.sportapp.models.news.domain.NewsEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.path
import kotlinx.serialization.json.Json

class NewsRepository {

    private val newsBaseUrl = "https://ac40-2604-2dc0-200-26c9-00.ngrok-free.app"
    private val controllerPath = "NewsController"

    val newsMapper = NewsMapper()


    private val json = Json {
        this.ignoreUnknownKeys = true
    }

    private val client = HttpClient {
        defaultRequest {
            this.url(newsBaseUrl)
        }
    }


    suspend fun getNews (): List<NewsEntity> {
        val builder = HttpRequestBuilder()
        builder.method = HttpMethod.Get

        builder.url {
            this.path(controllerPath)
        }


        val response = client.request(builder)

        val responseString: String = response.body()

        Log.d("tttNews", responseString)

        val newsResponse: List<NewsResponse> = json.decodeFromString(responseString)

        return newsMapper.getNewsEntityList(newsResponse)
    }
}
