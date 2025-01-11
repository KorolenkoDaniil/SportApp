package com.example.sportapp.models.soccer.domain

data class MatchDayEntity(
    val name: String,
    val matches: List<MatchEntity>,
)

