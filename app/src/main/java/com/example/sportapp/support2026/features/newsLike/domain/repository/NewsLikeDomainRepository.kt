package com.example.sportapp.support2026.features.newsLike.domain.repository

interface NewsLikeDomainRepository {
    suspend fun toggleStatus (newsId: Int, email: String) : Boolean
}