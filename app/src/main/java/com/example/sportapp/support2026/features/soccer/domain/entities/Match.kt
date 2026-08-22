package com.example.sportapp.support2026.features.soccer.domain.entities

data class Match(
    val matchID: Int = 0,
    val matchDateTime: String = "",
    val timeZoneID: String = "",
    val leagueId: Int = 0,
    val leagueName: String = "",
    val leagueSeason: Int = 0,
    val leagueShortcut: String = "",
    val matchDateTimeUTC: String = "",
    val group: Group? = null,
    val team1: Team? = null,
    val team2: Team? = null,
    val lastUpdateDateTime: String = "",
    val matchIsFinished: Boolean = false,
    val matchResults: List<MatchResult> = emptyList(),
    val goals: List<Goal> = emptyList(),
    val location: Location? = null,
    val numberOfViewers: Int = 0
)