package com.example.sportapp.api.entities.matchReport

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchReportResponse(
    @SerialName("page_index") val pageIndex: Int,
    @SerialName("page_size") val pageSize: Int,
    @SerialName("items_count") val itemsCount: Int,
    @SerialName("is_last_page") val isLastPage: Boolean,
    @SerialName("items") val items: List<EventResponse>
)
