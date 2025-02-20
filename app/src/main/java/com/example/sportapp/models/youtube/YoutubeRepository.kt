package com.example.sportapp.models.youtube

import android.util.Log
import com.example.sportapp.models.youtube.api.youtube.YoutubeSearchListResponse
import com.example.sportapp.models.youtube.domain.YoutubeSearchListResponseEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import kotlinx.serialization.json.Json

class YoutubeRepository {

    private val apiKey = "AIzaSyDW4nt60Fz-opw2g8iz8ZuulYWYO-Ar7ME"
    private val channelId = "UCxfPjORdISQSn2fV8tcVmgA"

    private val youtubeMapper = YoutubeMapper()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val client = HttpClient()

    suspend fun getVideos(): YoutubeSearchListResponseEntity? {
        // Формирование URL запроса
        val url = URLBuilder().apply {
            protocol = URLProtocol.HTTPS
            host = "www.googleapis.com"
            appendPathSegments("youtube", "v3", "search")
            parameters.append("key", apiKey)
            parameters.append("channelId", channelId)
            parameters.append("part", "snippet,id")
            parameters.append("order", "date")
            parameters.append("maxResults", "20")
        }.buildString()

        Log.d("tttVideos", "Request URL: $url")  // Логируем URL

        return try {
            // Выполнение запроса
            val response: HttpResponse = client.get(url)

            // Логируем статус-код
            Log.d("tttVideos", "Response status: ${response.status.value}")

            if (response.status.value == 200) {
                val responseString: String = response.body()
                Log.d("tttVideos", "Response: $responseString")  // Логируем ответ

                // Десериализация ответа
                val youtubeResponse: YoutubeSearchListResponse = json.decodeFromString(responseString)

                // Преобразование в доменный объект и возврат
                youtubeMapper.getListVideo(youtubeResponse)
            } else {
                Log.e("tttVideos", "Request failed with status: ${response.status.value}")
                null
            }
        } catch (e: Exception) {
            Log.e("tttVideos", "Error fetching videos: ${e.message}", e)
            null
        }
    }
}
