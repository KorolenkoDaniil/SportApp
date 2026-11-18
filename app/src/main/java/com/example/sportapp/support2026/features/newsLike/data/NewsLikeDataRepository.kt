package com.example.sportapp.support2026.features.newsLike.data

import com.example.sportapp.support2026.features.newsLike.data.api.NewsLikeApi
import com.example.sportapp.support2026.features.newsLike.data.dto.LikeResponseDto
import com.example.sportapp.support2026.features.newsLike.domain.repository.NewsLikeDomainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsLikeDataRepository @Inject constructor(
    private val api: NewsLikeApi
) : NewsLikeDomainRepository {

    override suspend fun toggleStatus(newsId: Int, email: String): Boolean {
        val responseResult : LikeResponseDto = api.toggleStatus(
            newsId = newsId,
            email = email
        )

        return responseResult.success
    }

}