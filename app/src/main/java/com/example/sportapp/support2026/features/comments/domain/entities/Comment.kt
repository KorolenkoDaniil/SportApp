package com.example.sportapp.support2026.features.comments.domain.entities

import java.time.LocalDateTime

data class Comment (
    val commentId: Int,
    val commentText: String,
    val newsId: Int,
    val email: String,
    val commentDateTime: LocalDateTime
)