package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchDto(
    @SerialName("matchID") val matchID: Int? = null,
    @SerialName("matchDateTime") val matchDateTime: String? = null,
    @SerialName("timeZoneID") val timeZoneID: String? = null,
    @SerialName("leagueId") val leagueId: Int? = null,
    @SerialName("leagueName") val leagueName: String? = null,
    @SerialName("leagueSeason") val leagueSeason: Int? = null,
    @SerialName("leagueShortcut") val leagueShortcut: String? = null,
    @SerialName("matchDateTimeUTC") val matchDateTimeUTC: String? = null,
    @SerialName("group") val group: GroupDto? = null,
    @SerialName("team1") val team1: TeamDto? = null,
    @SerialName("team2") val team2: TeamDto? = null,
    @SerialName("lastUpdateDateTime") val lastUpdateDateTime: String? = null,
    @SerialName("matchIsFinished") val matchIsFinished: Boolean? = null,
    @SerialName("matchResults") val matchResults: List<MatchResultDto>? = null,
    @SerialName("goals") val goals: List<GoalDto>? = null,
    @SerialName("location") val location: LocationDto? = null,
    @SerialName("numberOfViewers") val numberOfViewers: Int? = null
)