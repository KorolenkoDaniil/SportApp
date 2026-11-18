package com.example.sportapp.support2026.features.newsLike.domain.useCases

import com.example.sportapp.support2026.features.newsLike.domain.repository.NewsLikeDomainRepository
import javax.inject.Inject

class ToggleStatusUseCase @Inject constructor(
    private val newsLikeRepository: NewsLikeDomainRepository
){

    suspend operator fun invoke (newsId: Int, email: String): Boolean {
        return newsLikeRepository.toggleStatus(
            newsId = newsId,
            email = email
        )
    }
}