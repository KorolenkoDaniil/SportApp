package com.example.sportapp.models.youtube

import android.util.Log
import com.example.sportapp.models.youtube.api.youtube.YoutubeSearchListResponse
import com.example.sportapp.models.youtube.domain.YoutubeSearchListResponseEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.path
import kotlinx.serialization.json.Json

class YoutubeRepository {

    private val youtubeBaseUrl = "https://www.googleapis.com/youtube/v3/"
    private val apiKey = "AIzaSyDW4nt60Fz-opw2g8iz8ZuulYWYO-Ar7ME"
    private val channelId = "UCxfPjORdISQSn2fV8tcVmgA"


    val youtubeMapper = YoutubeMapper()


    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val client = HttpClient {
        defaultRequest {
            this.url(youtubeBaseUrl)
        }
    }


    suspend fun getVideos (): YoutubeSearchListResponseEntity {
        val builder = HttpRequestBuilder()
        builder.method = HttpMethod.Get

        builder.url {
            this.path("search")
            this.parameters.append("key", apiKey)
            this.parameters.append("channelId", channelId)
            this.parameters.append("part", "snippet,id")
            this.parameters.append("order", "date")
            this.parameters.append("maxResults", "20")
        }


        val response = client.request(builder)

        val responseString: String = response.body()

        Log.d("tttVideos", responseString)

        val youtubeResponse: YoutubeSearchListResponse = json.decodeFromString(responseString)

        return youtubeMapper.getListVideo(youtubeResponse)
    }
}
