package com.example.sportapp.support2026.features.comments.data.dto.comment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditCommentRequest (
    @SerialName("commentId") val commentId: Int,
    @SerialName("newsId") val newsId: Int,
    @SerialName("email") val email: String = "",
)