package com.example.sportapp.support2026.features.soccer.domain.entities

data class Goal(
    val goalID: Int = 0,
    val scoreTeam1: Int = 0,
    val scoreTeam2: Int = 0,
    val matchMinute: Int = 0,
    val goalGetterID: Int = 0,
    val goalGetterName: String = "",
    val scoringTeamId: Int = 0,
    val isPenalty: Boolean = false,
    val isOwnGoal: Boolean = false,
    val isOvertime: Boolean = false,
    val comment: String = ""
)