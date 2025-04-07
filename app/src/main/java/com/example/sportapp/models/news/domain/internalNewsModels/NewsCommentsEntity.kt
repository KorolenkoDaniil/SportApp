package com.example.sportapp.models.news.domain.internalNewsModels

data class NewsCommentsEntity(
    val commentId: Int,
    val newsDateTime: String,
    val commentDateTime: String,
    val commentText: String,
    val userEmail: String,
)
