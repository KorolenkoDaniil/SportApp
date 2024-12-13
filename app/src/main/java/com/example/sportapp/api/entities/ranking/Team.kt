package com.example.sportapp.api.entities.ranking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team (
    @SerialName("name") val name: String,
    @SerialName("full_name") val fullName: String,
    @SerialName("short_name") val shortName: String,
    @SerialName("team_id") val teamId: String,
    @SerialName("ranking") val ranking: Int,
    @SerialName("points") val points: Int,
    @SerialName("games_played") val gamesPlayed: Int,
    @SerialName("won") val won: Int,
    @SerialName("draws") val draws: Int,
    @SerialName("lost") val lost: Int,
    @SerialName("goals_made") val goalsMade: Int,
    @SerialName("goals_conceeded") val goalsConceeded: Int,
    @SerialName("points_home") val pointsHome: Int,
    @SerialName("played_home") val playedHome: Int,
    @SerialName("win_home") val winHome: Int,
    @SerialName("draws_home") val drawsHome: Int,
    @SerialName("lost_home") val lostHome: Int,
    @SerialName("goals_made_home") val goalsMadeHome: Int,
    @SerialName("goals_conceeded_home") val goalsConceededHome: Int,
    @SerialName("points_away") val pointsAway: Int,
    @SerialName("played_away") val playedAway: Int,
    @SerialName("won_away") val wonAway: Int,
    @SerialName("draws_away") val drawsAway: Int,
    @SerialName("lost_away") val lostAway: Int,
    @SerialName("goals_made_away") val goalsMadeAway: Int,
    @SerialName("goals_conceeded_away") val goalsConceededAway: Int,
    @SerialName("last_matches_results") val lastMatchesResults: List<LastMatchResult>,
)