package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchResultDto(
    @SerialName("resultID") val resultID: Int? = null,
    @SerialName("resultName") val resultName: String? = null,
    @SerialName("pointsTeam1") val pointsTeam1: Int? = null,
    @SerialName("pointsTeam2") val pointsTeam2: Int? = null,
    @SerialName("resultOrderID") val resultOrderID: Int? = null,
    @SerialName("resultTypeID") val resultTypeID: Int? = null,
    @SerialName("resultTypeKind") val resultTypeKind: String? = null,
    @SerialName("resultDescription") val resultDescription: String? = null
)