package com.example.sportapp.support2026.features.soccer.domain.entities

data class League(
    val leagueId: Int = 0,
    val leagueName: String = "",
    val leagueShortcut: String = "",
    val leagueSeason: String = "",
    val sport: Sport? = null
)
