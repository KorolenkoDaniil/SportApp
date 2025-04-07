package com.example.sportapp.models.news.domain

import com.example.sportapp.models.news.domain.internalNewsModels.NewsCommentsEntity
import com.example.sportapp.models.news.domain.internalNewsModels.NewsTagItem
import java.time.LocalDateTime

data class NewsEntity(
    val dateTime: LocalDateTime,
    val sport: String,
    val title: String,
    val imageId: String,
    val newsImage: String,
    val articleText: String,
    val tags: List<NewsTagItem>,
    val comments: List<NewsCommentsEntity>,
)




