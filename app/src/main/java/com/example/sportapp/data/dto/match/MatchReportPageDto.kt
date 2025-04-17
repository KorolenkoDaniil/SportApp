package com.example.sportapp.CleanArchitexture.data.dto.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//класс, представления списка событий в матче
@Serializable
data class MatchReportPageDto(
    @SerialName("page_index") val pageIndex: Int,
    @SerialName("page_size") val pageSize: Int,
    @SerialName("items_count") val itemsCount: Int,
    @SerialName("is_last_page") val isLastPage: Boolean,
    @SerialName("items") val items: List<EventDto>
)
