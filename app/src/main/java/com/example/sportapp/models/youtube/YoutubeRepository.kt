package com.example.sportapp.models.youtube

import android.util.Log
import com.example.sportapp.models.youtube.api.youtube.YoutubeSearchListResponse
import com.example.sportapp.models.youtube.domain.VideoPlayListResponseEntity
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
    private var nextPageToken: String? = null

    private val youtubeMapper = YoutubeMapper()
    private val json = Json { ignoreUnknownKeys = true }
    private val client = HttpClient()

    suspend fun getVideos(): VideoPlayListResponseEntity? {
        val urlBuilder = URLBuilder().apply {
            protocol = URLProtocol.HTTPS
            host = "www.googleapis.com"
            appendPathSegments("youtube", "v3", "search")
            parameters.append("key", apiKey)
            parameters.append("channelId", channelId)
            parameters.append("part", "snippet,id")
            parameters.append("order", "date")
            parameters.append("maxResults", "20")

            nextPageToken?.let {
                parameters.append("pageToken", it)
            }
        }

        val url = urlBuilder.buildString()
        Log.d("tttVideos", "Request URL: $url")

        return try {
            val response: HttpResponse = client.get(url)

            Log.d("tttVideos", "Response status: ${response.status.value}")

            if (response.status.value == 200) {
                val responseString: String = response.body()
                Log.d("tttVideos", "Response: $responseString")

                val youtubeResponse: YoutubeSearchListResponse = json.decodeFromString(responseString)

                nextPageToken = youtubeResponse.nextPageToken

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

    fun resetPagination() {
        nextPageToken = null // Сбрасываем пагинацию (например, при новом поиске)
    }
}
