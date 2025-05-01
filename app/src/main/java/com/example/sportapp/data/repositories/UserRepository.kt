package com.example.sportapp.CleanArchitexture.data.repositories

import android.util.Log
import com.example.sportapp.CleanArchitexture.data.dto.user.UserDto
import com.example.sportapp.CleanArchitexture.data.mappers.UserMapper
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.data.BaseUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.io.File

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
            val currentUser: UserDto = json.decodeFromString(response.body())
            return userMapper.UserResponseToEntity(currentUser)
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


        val currentUser: UserDto = json.decodeFromString(response.body())
        return userMapper.UserResponseToEntity(currentUser)
    }



    @OptIn(InternalAPI::class)
    suspend fun uploadImage(imageFile: File): HttpResponse {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }

        return client.post("https://yourapi.com/upload"){
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append(
                            "image",
                            imageFile.readBytes(),
                            Headers.build {
                                append(HttpHeaders.ContentDisposition, "form-data; name=image; filename=\"${imageFile.name}\"")
                                append(HttpHeaders.ContentType, ContentType.Image.JPEG)
                            }
                        )
                    }
                )
            )
        }
    }
}






