package com.example.sportapp.models.soccer.api.domain

data class MatchDayEntity(
    val name: String,
    val matches: List<MatchEntity>,
)

