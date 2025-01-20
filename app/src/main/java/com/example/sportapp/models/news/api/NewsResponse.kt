package com.example.sportapp.models.news.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("pageNumber") val pageNumber: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("totalItems") val totalItems: Int,
    @SerialName("totalPages") val totalPages: Int,
    @SerialName("news") val news: List<NewsItemResponse>,
)


