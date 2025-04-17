package com.example.sportapp.CleanArchitexture.data.dto.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchPageDto (
    @SerialName("page_index") val pageIndex: Int? = null,
    @SerialName("page_size") val pageSize: Int? = null,
    @SerialName("items_count") val itemsCount: Int? = null,
    @SerialName("is_last_page") val isLastPage: Boolean? = null,
    @SerialName("items") val items: List<MatchDto>? = null
)