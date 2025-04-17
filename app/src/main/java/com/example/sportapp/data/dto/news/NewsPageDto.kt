package com.example.sportapp.CleanArchitexture.data.dto.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsPageDto (
    @SerialName("pageNumber") val pageNumber: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("totalItems") val totalItems: Int,
    @SerialName("totalPages") val totalPages: Int,
    @SerialName("news") val news: List<NewsDto>,
)


