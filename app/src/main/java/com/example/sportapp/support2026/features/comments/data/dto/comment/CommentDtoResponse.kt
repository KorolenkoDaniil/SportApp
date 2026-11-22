package com.example.sportapp.support2026.features.comments.data.dto.comment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentDtoResponse (
    @SerialName("commentId") val commentId: Int,
    @SerialName("commentText") val commentText: String = "",
    @SerialName("newsId") val newsId: Int,
    @SerialName("email") val email: String = "",
    @SerialName("commentDateTime") val commentDateTime: String,
)