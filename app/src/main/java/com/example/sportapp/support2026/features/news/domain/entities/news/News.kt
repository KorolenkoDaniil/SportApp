package com.example.sportapp.support2026.features.news.domain.entities.news

import java.time.LocalDateTime

data class News(
    val dateTime: LocalDateTime,
    val sport: String,
    val imageId: String,
    val text: String,
    val title: String
)