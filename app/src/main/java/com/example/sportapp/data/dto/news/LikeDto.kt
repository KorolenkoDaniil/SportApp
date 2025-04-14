package com.example.sportapp.data.dto.news

import kotlinx.serialization.Serializable

@Serializable
data class LikeDto (
    val newsDateTime: String,
    val userEmail: String,
)


