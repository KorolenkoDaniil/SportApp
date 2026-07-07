package com.example.sportapp.support2026.features.news.news.data.dto.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NewsDto (
    @SerialName("dateTime") val dateTime: String?,
    @SerialName("sport") val sport: String?,
    @SerialName("imageId") val imageId: String?,
    @SerialName("articleText") val text: String?,
    @SerialName("title") val title: String?,

)


