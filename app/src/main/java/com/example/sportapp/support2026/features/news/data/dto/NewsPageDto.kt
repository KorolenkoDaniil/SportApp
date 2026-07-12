package com.example.sportapp.support2026.features.news.data.dto

import com.example.sportapp.support2026.features.news.data.dto.components.NewsDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NewsPageDto (
    @SerialName("pageNumber") val pageNumber: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("totalItems") val totalItems: Int,
    @SerialName("totalPages") val totalPages: Int,
    @SerialName("itemsList") val itemsList: List<NewsDto>?,
)



