package com.example.sportapp.models.news

import com.example.sportapp.models.aiAnswer.AIAnswerEntity
import com.example.sportapp.models.aiAnswer.AIAnswerResponse

class AIAnswerMapper {

    fun getAIAnswerEntity(response: AIAnswerResponse): AIAnswerEntity {
        return AIAnswerEntity(
            answer = response.answer
        )
    }
}