package com.example.sportapp.CleanArchitexture.data.repositories

import android.util.Log
import com.example.sportapp.CleanArchitexture.data.dto.comments.CommentDto
import com.example.sportapp.CleanArchitexture.data.dto.comments.CommentsPageDto
import com.example.sportapp.CleanArchitexture.data.mappers.CommentsMapper
import com.example.sportapp.CleanArchitexture.data.mappers.CommentsPageMapper
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentEntity
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentsPageEntity
import com.example.sportapp.data.BaseUrl
import com.example.sportapp.data.dto.responseDTO.AddCommentLike
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//TODO сделать оставить в репозитории только вызовы методов, а сами методы расписать в usecase

class CommentRepository {

    private val commentsPageMapper = CommentsPageMapper()
    private val commentsMapper = CommentsMapper()


    private val json = Json { ignoreUnknownKeys = true }
    private val client = HttpClient()

    private val controllerPath = "Comments"
    private val getCommentsController = "GetComments"

    suspend fun getComments(newsDateTime: LocalDateTime, pageNumber: Int, viewer: String): CommentsPageEntity? {
        val isoDateTime = newsDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                takeFrom(BaseUrl)
                path("/$controllerPath/$getCommentsController")
                parameters.append("itemId", isoDateTime)
                parameters.append("pageNumber", pageNumber.toString())
                parameters.append("pageSize", "10")
                parameters.append("Viewer", viewer)
            }
        }

        val requestUrl = builder.url.toString()
        Log.d("tttComments", "Request URL: $requestUrl")

        return try {
            val response = client.request(builder)
            Log.d("tttComments", "Response status: ${response.status}")

            val responseString = response.body<String>()
            Log.d("tttComments", "Response body: $responseString")

            val commentsResponse: CommentsPageDto = json.decodeFromString(responseString)
            commentsPageMapper.getListComments(commentsResponse)

        } catch (e: Exception) {
            Log.e("tttComments", "Error fetching comments: ${e.message}", e)
            null
        }
    }




    suspend fun putComment(comment: CommentEntity): CommentEntity {
        val commentDto = commentsMapper.convertToDto(comment)
        val jsonBody = json.encodeToString(CommentDto.serializer(), commentDto)


        Log.d("tttComment", "⏱ Отправка комментария:")
        Log.d("tttComment", "➡ URL: $BaseUrl/Comments/PutComment")
        Log.d("tttComment", "➡ Тело запроса: $jsonBody")

        try {
            val response = client.post("$BaseUrl/Comments/PutComment") {
                contentType(ContentType.Application.Json)
                setBody(jsonBody)
            }

            Log.d("tttComment", "Статус ответа: ${response.status}")
            val responseText = response.bodyAsText()
            Log.d("tttComment", "Тело ответа: $responseText")

            if (response.status == HttpStatusCode.OK) {
                val addedComment: CommentDto = json.decodeFromString(responseText)
                Log.d("tttComment", "🎉 Добавлен комментарий: $addedComment")
                return commentsMapper.convertToEntity(addedComment)
            } else {
                throw Exception("Ошибка при добавлении комментария: ${response.status}")
            }

        } catch (e: Exception) {
            Log.e("tttComment", "Ошибка при отправке комментария: ${e.message}", e)
            throw e
        }
    }




    suspend fun putCommentLike (likeAuthorEmail: String, commentId: Int): Int {

        val addCommentLike = AddCommentLike(
            LikeAuthor = likeAuthorEmail,
            CommentId = commentId
        )
        val jsonBody = json.encodeToString(AddCommentLike.serializer(), addCommentLike)

        Log.d("tttComment", "⏱ Отправка лайка комментария:")
        Log.d("tttComment", "➡ URL: $BaseUrl/Comments/AddLike")
        Log.d("tttComment", "➡ Тело запроса: $jsonBody")

        try {
            val response = client.post("$BaseUrl/Comments/AddLike") {
                contentType(ContentType.Application.Json)
                setBody(jsonBody)
            }

            Log.d("tttComment", "Статус ответа: ${response.status}")
            val responseText = response.bodyAsText()
            Log.d("tttComment", "Тело ответа: $responseText")

            if (response.status == HttpStatusCode.OK) {
                val addedCommentLike: Int = response.bodyAsText().toInt()
                Log.d("tttComment", "Добавлен дайк к комментарию: $addedCommentLike")
                return addedCommentLike
            } else {
                throw Exception("Ошибка при добавлении к комментарию лайка : ${response.status}")
            }

        } catch (e: Exception) {
            Log.e("tttComment", "Ошибка при отправке лайка комментария: ${e.message}", e)
            throw e
        }
    }



    suspend fun removeCommentLike (likeAuthorEmail: String, commentId: Int): Int {

        val addCommentLike = AddCommentLike(
            LikeAuthor = likeAuthorEmail,
            CommentId = commentId
        )
        val jsonBody = json.encodeToString(AddCommentLike.serializer(), addCommentLike)

        Log.d("tttComment", "⏱ Отправка удаления лайка комментария")
        Log.d("tttComment", "➡ URL: $BaseUrl/Comments/AddLike")
        Log.d("tttComment", "➡ Тело запроса: $jsonBody")

        try {
            val response = client.post("$BaseUrl/Comments/RemoveLike") {
                contentType(ContentType.Application.Json)
                setBody(jsonBody)
            }

            Log.d("tttComment", "Статус ответа: ${response.status}")
            val responseText = response.bodyAsText()
            Log.d("tttComment", "Тело ответа: $responseText")

            if (response.status == HttpStatusCode.OK) {
                val NumberOfLikes: Int = response.bodyAsText().toInt()
                Log.d("tttComment", "убран  дайк к комментарию: $NumberOfLikes")
                return NumberOfLikes
            } else {
                throw Exception("Ошибка при удалении  к комментарию лайка : ${response.status}")
            }

        } catch (e: Exception) {
            Log.e("tttComment", "Ошибка при удалении лайка комментария: ${e.message}", e)
            throw e
        }
    }
}
