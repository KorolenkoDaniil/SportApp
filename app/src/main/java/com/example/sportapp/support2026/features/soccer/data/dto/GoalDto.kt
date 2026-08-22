package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoalDto(
    @SerialName("goalID") val goalID: Int? = null,
    @SerialName("scoreTeam1") val scoreTeam1: Int? = null,
    @SerialName("scoreTeam2") val scoreTeam2: Int? = null,
    @SerialName("matchMinute") val matchMinute: Int? = null,
    @SerialName("goalGetterID") val goalGetterID: Int? = null,
    @SerialName("goalGetterName") val goalGetterName: String? = null,
    @SerialName("scoringTeamId") val scoringTeamId: Int? = null,
    @SerialName("isPenalty") val isPenalty: Boolean? = null,
    @SerialName("isOwnGoal") val isOwnGoal: Boolean? = null,
    @SerialName("isOvertime") val isOvertime: Boolean? = null,
    @SerialName("comment") val comment: String? = null
)