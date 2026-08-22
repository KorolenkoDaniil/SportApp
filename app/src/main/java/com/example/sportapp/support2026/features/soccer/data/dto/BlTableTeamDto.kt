package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlTableTeamDto(
    @SerialName("teamInfoId") val teamInfoId: Int? = null,
    @SerialName("teamName") val teamName: String? = null,
    @SerialName("shortName") val shortName: String? = null,
    @SerialName("teamIconUrl") val teamIconUrl: String? = null,
    @SerialName("points") val points: Int? = null,
    @SerialName("opponentGoals") val opponentGoals: Int? = null,
    @SerialName("goals") val goals: Int? = null,
    @SerialName("matches") val matches: Int? = null,
    @SerialName("won") val won: Int? = null,
    @SerialName("lost") val lost: Int? = null,
    @SerialName("draw") val draw: Int? = null,
    @SerialName("goalDiff") val goalDiff: Int? = null
)