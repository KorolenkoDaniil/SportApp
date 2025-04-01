package com.example.sportapp.models.news

import com.example.sportapp.models.aiAnswer.AIAnswerResponse
import com.example.sportapp.models.chatMessage.MessageEntity

class AIAnswerMapper {

    fun getAIAnswerEntity(response: AIAnswerResponse, user: String ): MessageEntity {
        return MessageEntity(
            text = response.answer,
            sender = "AI",
            user = user
        )
    }
}