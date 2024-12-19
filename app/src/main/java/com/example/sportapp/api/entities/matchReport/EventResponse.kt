package com.example.sportapp.api.entities.matchReport

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventResponse(
    @SerialName("type") val type: String,
    @SerialName("match_phase") val matchPhase: String?,
    @SerialName("order") val order: Int,
    @SerialName("id") val id: String,
    @SerialName("team_id") val teamId: String?,
    @SerialName("player_id") val playerId: String?,
    @SerialName("player_surname") val playerSurname: String?,
    @SerialName("name") val name: String?,
    @SerialName("player_full_name") val playerFullName: String?,
    @SerialName("player_shirt_name") val playerShirtName: String?,
    @SerialName("minute") val minute: Int?
)
