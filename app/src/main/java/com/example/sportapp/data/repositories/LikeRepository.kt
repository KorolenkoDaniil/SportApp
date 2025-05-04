package com.example.sportapp.CleanArchitexture.data.repositories

import android.util.Log
import com.example.sportapp.data.BaseUrl
import com.example.sportapp.data.dto.news.LikeDto
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDateTime

//TODO сделать оставить в репозитории только вызовы методов, а сами методы расписать в usecase

class LikeRepository {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {  }
    }


    suspend fun putLike (newsDateTime: LocalDateTime, userEmail: String) {

        val likeDto = LikeDto(
            newsDateTime  = newsDateTime.toString(),
            userEmail = userEmail
        )

        val requestBody = Json.encodeToString(likeDto)

        client.post("$BaseUrl/NewsController/AddLike") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }


    suspend fun removeLike (newsDateTime: LocalDateTime, userEmail: String) {

        val likeDto = LikeDto(
            newsDateTime  = newsDateTime.toString(),
            userEmail = userEmail
        )

        val requestBody = Json.encodeToString(likeDto)

        client.post("$BaseUrl/NewsController/RemoveLike") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }



    suspend fun LikeExist (newsDateTime: LocalDateTime, userEmail: String): Boolean {

        val likeDto = LikeDto(
            newsDateTime  = newsDateTime.toString(),
            userEmail = userEmail
        )

        val requestBody = Json.encodeToString(likeDto)

        val response = client.post("$BaseUrl/NewsController/ExistLike") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }

        val likeExist = response.bodyAsText()

        Log.d("lllike", (likeExist.toBooleanStrictOrNull() ?: false ).toString())

        return likeExist.toBooleanStrictOrNull() ?: false
    }
}
