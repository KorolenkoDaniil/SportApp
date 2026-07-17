package com.example.sportapp.support2026.features.news.domain.entities.news

import java.time.LocalDateTime

data class News(
    val id: Int,
    val dateTime: LocalDateTime,
    val sport: String,
    val imageUrl: String,
    val text: String,
    val title: String
)