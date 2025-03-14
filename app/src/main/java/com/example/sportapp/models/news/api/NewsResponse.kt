package com.example.sportapp.models.news.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("PageNumber") val pageNumber: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("TotalItems") val totalItems: Int,
    @SerialName("TotalPages") val totalPages: Int,
    @SerialName("News") val news: List<NewsItemResponse>,
)


