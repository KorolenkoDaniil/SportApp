package com.example.sportapp.support2026.features.news.data.dto.newsList.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NewsDto (
    @SerialName("id") val id: Int?,
    @SerialName("dateTime") val dateTime: String?,
    @SerialName("sport") val sport: String?,
    @SerialName("imageId") val imageId: String?,
    @SerialName("articleText") val text: String?,
    @SerialName("title") val title: String?,
)


