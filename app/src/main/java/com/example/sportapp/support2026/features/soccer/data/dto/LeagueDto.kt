package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueDto(
    @SerialName("leagueId") val leagueId: Int? = null,
    @SerialName("leagueName") val leagueName: String? = null,
    @SerialName("leagueShortcut") val leagueShortcut: String? = null,
    @SerialName("leagueSeason") val leagueSeason: String? = null,
    @SerialName("sport") val sport: SportDto? = null
)