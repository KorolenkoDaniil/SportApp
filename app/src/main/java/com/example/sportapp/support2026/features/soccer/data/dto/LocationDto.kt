package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("locationID") val locationID: Int? = null,
    @SerialName("locationCity") val locationCity: String? = null,
    @SerialName("locationStadium") val locationStadium: String? = null
)