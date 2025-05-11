package com.example.sportapp.CleanArchitexture.data.mappers

import com.example.sportapp.CleanArchitexture.data.dto.aiAnswer.MessageDto
import com.example.sportapp.CleanArchitexture.domain.models.aiAnswer.MessageEntity
import com.example.sportapp.data.dto.aiAnswer.MessagesPageDto
import com.example.sportapp.domain.models.aiAnswer.MessagePageEntity

class AIAnswerMapper {

    fun convertToEntity(message: MessageDto): MessageEntity {
        return MessageEntity(
            userEmail = message.userEmail,
            messageText = message.messageText,
            isAiAnswer = message.isAiAnswer
        )
    }

    fun convertToPageEntity(response: MessagesPageDto): MessagePageEntity {
        return MessagePageEntity(pageNumber = response.pageNumber,
            pageSize = response.pageSize,
            totalItems = response.totalItems,
            totalPages = response.totalPages,
            messages = response.messages.map { item ->
                MessageEntity(
                    userEmail = item.userEmail,
                    messageText = item.messageText,
                    isAiAnswer = item.isAiAnswer
                )
            }
        )
    }
}
