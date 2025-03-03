package com.example.sportapp.models.news

import com.example.sportapp.models.aiAnswer.AIAnswerResponse
import com.example.sportapp.models.chatMessage.MessageEntity
import java.time.LocalDateTime

class AIAnswerMapper {

    fun getAIAnswerEntity(response: AIAnswerResponse): MessageEntity {
        return MessageEntity(
            text = response.answer,
            time = LocalDateTime.now(),
            sender = "AI"
        )
    }
}