package com.example.sportapp.support2026.features.soccer.domain.entities

data class MatchResult(
    val resultID: Int = 0,
    val resultName: String = "",
    val pointsTeam1: Int = 0,
    val pointsTeam2: Int = 0,
    val resultOrderID: Int = 0,
    val resultTypeID: Int = 0,
    val resultTypeKind: String = "",
    val resultDescription: String = ""
)
