package com.example.sportapp.support2026.features.newsLike.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsLikeDto (
    @SerialName ("newsId") val newsId: Int,
    @SerialName ("email") val email: String,
)

@Serializable
data class LikeResponseDto (
    @SerialName ("success") val success: Boolean
)
