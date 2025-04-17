package com.example.sportapp.CleanArchitexture.domain.models.news

import java.time.LocalDateTime

data class NewsEntity(
    val dateTime: LocalDateTime,
    val sport: String,
    val title: String,
    val imageId: String,
    val newsImage: String,
    val articleText: String,
    val commentsCount: Int,
    val likesCount: Int,
    var isLiked: Boolean,
    val tags: List<NewsTagEntity>,
)




