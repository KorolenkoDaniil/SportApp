package com.example.sportapp.models.news.domain

import java.time.LocalDateTime

data class NewsEntity(
    val dateTime: LocalDateTime,
    val sport: String,
    val title: String,
    val imageId: String,
    val newsImage: String,
    val articleText: String,
    val tags: List<NewsTagItem>
)




