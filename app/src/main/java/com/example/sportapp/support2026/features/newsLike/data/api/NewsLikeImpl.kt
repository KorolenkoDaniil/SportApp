
package com.example.sportapp.support2026.features.newsLike.data.api

import android.util.Log
import com.example.sportapp.support2026.app.baseUrl
import com.example.sportapp.support2026.features.newsLike.data.dto.LikeResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class NewsLikeImpl @Inject constructor(
    private val client: HttpClient
) : NewsLikeApi {

    override suspend fun toggleStatus(newsId: Int, email: String): LikeResponseDto {
        return try {
            val httpResponse: HttpResponse = client.post("${baseUrl}/api/NewsLike/toggle/$newsId") {
                parameter("email", email)
            }

            val rawBody = httpResponse.bodyAsText()
            Log.d("tttNews", "Raw server response: $rawBody")

            val response: LikeResponseDto = httpResponse.body()

            response
        } catch (e: Exception) {
            Log.e("tttNews", "Error toggling like: ${e.localizedMessage}", e)

             LikeResponseDto(success = false)
        }
    }
}