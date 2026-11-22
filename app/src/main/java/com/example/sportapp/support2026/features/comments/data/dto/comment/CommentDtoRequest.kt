package com.example.sportapp.support2026.features.comments.data.dto.comment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentDtoRequest (
    @SerialName("commentText") val text: String = "",
    @SerialName("newsId") val newsId: Int,
    @SerialName("email") val email: String = "",
)