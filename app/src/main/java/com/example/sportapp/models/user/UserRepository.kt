package com.example.sportapp.models.user

import android.util.Log
import com.example.sportapp.models.BaseUrl
import com.example.sportapp.models.user.api.UserItemResponse
import com.example.sportapp.models.user.domain.UserEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class UserRepository {

    val userMapper = UserMapper()

    private val json = Json {
        this.ignoreUnknownKeys = true
    }

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }


    suspend fun putNewUser(email: String) : UserEntity {

        val response = client.post("$BaseUrl/api/users") {
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("email", email)
            })
        }

        Log.d("User response", "Status: ${response.status}, Body: ${response.bodyAsText()}")
        if (response.status == HttpStatusCode.OK) {
            val currentUser: UserItemResponse = json.decodeFromString(response.body())
            return userMapper.UseResponseToEntity(currentUser)
        } else {
            throw Exception("Failed to create user: ${response.status}")
        }
    }


    suspend fun getUser(email: String): UserEntity {

        Log.d("email", email)

        val response = client.get("$BaseUrl/api/users/GetUser") {
            url {
                parameters.append("email", email)
            }
        }

        Log.d("User response", response.bodyAsText())


        val currentUser: UserItemResponse = json.decodeFromString(response.body())
        return userMapper.UseResponseToEntity(currentUser)
    }


}






