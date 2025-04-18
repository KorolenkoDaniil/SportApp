package com.example.sportapp.data.dto.responseDTO

import kotlinx.serialization.Serializable

@Serializable
data class AddCommentLike (
    val LikeAuthor: String,
    val CommentId: Int
)
