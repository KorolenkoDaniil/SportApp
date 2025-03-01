package com.example.sportapp.models.news

import android.util.Log
import com.example.sportapp.models.BaseUrl
import com.example.sportapp.models.aiAnswer.AIAnswerEntity
import com.example.sportapp.models.aiAnswer.AIAnswerResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AIAnswerRepository {

    val answerMapper = AIAnswerMapper()

    private val json = Json {
        this.ignoreUnknownKeys = true
    }

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }


    suspend fun askIA(prompt: String): AIAnswerEntity {
        val requestBody = Json.encodeToString(mapOf("prompt" to prompt))

        val response = client.post("$BaseUrl/gemini/ask") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }

        Log.d("AIAnswer", "Status: ${response.status}, Body: ${response.bodyAsText()}")
        if (response.status == HttpStatusCode.OK) {
            val answer: AIAnswerResponse = json.decodeFromString(response.body())
            return answerMapper.getAIAnswerEntity(answer)
        } else {
            throw Exception("Failed to ask AI: ${response.status}")
        }
    }

}






