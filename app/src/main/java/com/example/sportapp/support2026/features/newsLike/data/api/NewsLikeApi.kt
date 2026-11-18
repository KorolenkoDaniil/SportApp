package com.example.sportapp.support2026.features.newsLike.data.api

import com.example.sportapp.support2026.features.newsLike.data.dto.LikeResponseDto

interface  NewsLikeApi {
    suspend fun toggleStatus (newsId: Int, email: String) : LikeResponseDto
}