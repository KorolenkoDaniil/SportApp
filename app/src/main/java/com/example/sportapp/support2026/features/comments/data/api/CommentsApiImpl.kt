package com.example.sportapp.support2026.features.comments.data.api

import android.util.Log
import com.example.sportapp.support2026.app.baseUrl
import com.example.sportapp.support2026.features.comments.data.dto.comment.CommentDtoRequest
import com.example.sportapp.support2026.features.comments.data.dto.comment.CommentDtoResponse
import com.example.sportapp.support2026.features.comments.data.dto.comment.EditCommentRequest
import com.example.sportapp.support2026.features.comments.data.dto.commentList.CommentDtoPage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import javax.inject.Inject

class CommentsApiImpl @Inject constructor  (
    private val client: HttpClient
    ) : CommentsApi {

    override suspend fun getPaginatedCommentsList(
        pageNumber: Int,
        newsId: Int,
        email: String?,
    ): CommentDtoPage {
        val httpResponse: HttpResponse = client.get("${baseUrl}/Comments/GetComments") {
            if (!email.isNullOrBlank()) {
                parameter("email", email)
            }
            parameter("newsId", newsId)
            parameter("pageNumber", pageNumber)
        }

        if (!httpResponse.status.isSuccess()) {
            val errorBody = runCatching { httpResponse.bodyAsText() }.getOrDefault("")
            throw Exception("GetComments failed: ${httpResponse.status} $errorBody")
        }

        val response: CommentDtoPage = httpResponse.body()
        Log.d("tttComments", "$response")
        return response
    }

    override suspend fun addNewComment(commentDtoRequest: CommentDtoRequest): CommentDtoResponse {
        val httpResponse: HttpResponse = client.post ("${baseUrl}/Comments/AddNewComment"){
            contentType(ContentType.Application.Json)
            setBody(commentDtoRequest)
        }

        if (httpResponse.status.isSuccess()) {

            val response: CommentDtoResponse = httpResponse.body()
            Log.d("tttComments", "$response")
            return response
        } else {
            throw Exception("Server returned status: ${httpResponse.status}")
        }
    }

    override suspend fun deleteComment(commentId: Int): Boolean {
        val httpResponse: HttpResponse = client.delete ("${baseUrl}/Comments/DeleteComment/${commentId}")

        if (httpResponse.status.isSuccess()) {

            val response: Boolean = httpResponse.body()
            Log.d("tttComments", "$response")
            return response
        } else {
            throw Exception("Server returned status: ${httpResponse.status}")
        }
    }

    override suspend fun editComment(dto: EditCommentRequest): Boolean {
        val httpResponse: HttpResponse = client.put ("${baseUrl}/Comments/EditComment"){
            contentType(ContentType.Application.Json)
            setBody(dto)
        }

        if (httpResponse.status.isSuccess()) {

            val response: Boolean = httpResponse.body()
            Log.d("tttComments", "$response")
            return response
        } else {
            throw Exception("Server returned status: ${httpResponse.status}")
        }
    }
}