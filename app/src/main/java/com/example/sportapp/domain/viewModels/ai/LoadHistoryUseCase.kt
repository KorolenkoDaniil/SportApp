package com.example.sportapp.domain.viewModels.ai

import com.example.sportapp.CleanArchitexture.data.repositories.MessageRepository
import com.example.sportapp.domain.models.aiAnswer.MessagePageEntity

class LoadHistoryUseCase {

    suspend fun loadHistoryUseCase(pageNumber: Int, email: String, repository: MessageRepository): MessagePageEntity {
        return repository.loadChatHistory(
            pageNumber = pageNumber,
            email = email)
    }
}