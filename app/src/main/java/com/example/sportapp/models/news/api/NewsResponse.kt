package com.example.sportapp.models.news.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("date_time") val dateTime: String,
    @SerialName("sport") val sport: String,
    @SerialName("title") val title: String,
    @SerialName("image_id") val imageId: String,
)