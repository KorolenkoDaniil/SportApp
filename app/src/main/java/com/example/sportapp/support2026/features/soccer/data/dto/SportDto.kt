package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class SportDto(
    @SerialName("sportId") val sportId: Int? = null,
    @SerialName("sportName") val sportName: String? = null
)