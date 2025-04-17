package com.example.sportapp.CleanArchitexture.domain.models.match

data class MatchDayEntity(
    val name: String,
    val matches: List<MatchEntity>,
)

