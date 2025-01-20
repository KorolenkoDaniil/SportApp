package com.example.sportapp.models.news.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NewsItemResponse (
    @SerialName("dateTime") val dateTime: String,
    @SerialName("sport") val sport: String,
    @SerialName("title") val title: String,
    @SerialName("imageId") val imageId: String,
    @SerialName("articleText") val articleText: String
)
