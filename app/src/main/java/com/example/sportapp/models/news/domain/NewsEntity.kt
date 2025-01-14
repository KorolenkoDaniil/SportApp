package com.example.sportapp.models.news.domain

import kotlinx.serialization.Serializable

@Serializable
data class NewsEntity(
    val dateTime: String,
    val sport: String,
    val text: String,
)