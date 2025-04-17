package com.example.sportapp.CleanArchitexture.data.mappers

import com.example.sportapp.CleanArchitexture.data.dto.aiAnswer.AIAnswerDto
import com.example.sportapp.CleanArchitexture.domain.models.aiAnswer.MessageEntity

class AIAnswerMapper {

    fun getAIAnswerEntity(response: AIAnswerDto, user: String ): MessageEntity {
        return MessageEntity(
            text = response.answer,
            sender = "AI",
            user = user
        )
    }
}