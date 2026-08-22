package com.example.sportapp.support2026.features.user.data.api

import android.util.Log
import com.example.sportapp.support2026.app.baseUrl
import com.example.sportapp.support2026.features.user.data.dto.requests.CreateUserRequestDto
import com.example.sportapp.support2026.features.user.data.dto.requests.UserRequestDto
import com.example.sportapp.support2026.features.user.data.dto.response.UserResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import javax.inject.Inject

class UserApiImpl @Inject constructor (
    private val client: HttpClient
) : UserApi {

    override suspend fun addUser(userRequestDto: CreateUserRequestDto): UserResponseDto {
        Log.d("signup", "userRequestDto $userRequestDto")

        val httpResponse = client.post("$baseUrl/api/User/AddUser") {
            contentType(ContentType.Application.Json)
            setBody(userRequestDto)
        }

        val response: UserResponseDto = httpResponse.body()
        Log.d("tttUser", "$response")

        return response
    }




    override suspend fun getUser(dto: UserRequestDto): UserResponseDto {
        val httpResponse = client.post("$baseUrl/api/User/GetUser") {
            contentType(ContentType.Application.Json)
            setBody(dto)
        }

        if (httpResponse.status.isSuccess()) {
            return httpResponse.body()
        } else {
            throw Exception("Server returned status: ${httpResponse.status}")
        }
    }

    override suspend fun checkUserOnServer(email: String): Boolean {
        val exists: Boolean = client.get("$baseUrl/api/User/exists") {
            parameter("email", email)
        }.body()

        Log.d("tttUser", "Is user exists: $exists")

        return exists
    }

}