package com.example.sportapp.api.entities.youtube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    @SerialName("totalResults") val totalResults: Int? = null,
    @SerialName("resultsPerPage") val resultsPerPage: Int? = null,
)


