package com.example.sportapp.models.news.api.internalNewsModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsTagResponse (
    @SerialName("tag_id") val tagId: Int,
    @SerialName("tag") val tag: String,
    @SerialName("news_date_time") val newsDateTime: String,
)