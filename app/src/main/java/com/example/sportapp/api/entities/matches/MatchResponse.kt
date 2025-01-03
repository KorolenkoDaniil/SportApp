package com.example.sportapp.api.entities.matches

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//класс, представления 1 матча
@Serializable
data class MatchResponse(
    @SerialName("page_index") val pageIndex: Int? = null,
    @SerialName("page_size") val pageSize: Int? = null,
    @SerialName("items_count") val itemsCount: Int? = null,
    @SerialName("is_last_page") val isLastPage: Boolean? = null,
    @SerialName("items") val items: List<MatchItem>? = null
)