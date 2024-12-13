package com.example.sportapp.api.entities.ranking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse (
    @SerialName("page_index") val pageIndex: Int,
    @SerialName ("page_size") val pageSize: Int,
    @SerialName ("items_count") val itemsCount: Int,
    @SerialName ("is_last_page") val isLastPage: Boolean,
    @SerialName ("items") val items: List<Team>,
)