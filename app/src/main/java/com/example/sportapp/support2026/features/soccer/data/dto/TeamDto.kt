package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamDto(
    @SerialName("teamId") val teamId: Int? = null,
    @SerialName("teamName") val teamName: String? = null,
    @SerialName("shortName") val shortName: String? = null,
    @SerialName("teamIconUrl") val teamIconUrl: String? = null,
    @SerialName("teamGroupName") val teamGroupName: String? = null
)