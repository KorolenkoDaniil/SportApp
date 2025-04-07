package com.example.sportapp.models.news.api.internalNewsModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NewsCommentsResponse(
    @SerialName("comment_id") val commentId: Int,
    @SerialName("news_date_time") val newsDateTime: String,
    @SerialName("comment_date_time") val commentDateTime: String,
    @SerialName("comment_text") val commentText: String,
    @SerialName("user_email") val userEmail: String,
)
