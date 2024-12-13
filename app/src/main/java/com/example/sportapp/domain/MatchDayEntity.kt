package com.example.sportapp.domain

import com.example.sportapp.api.entities.matches.MatchItem

data class MatchDayEntity(
    val name: String,
    val matches: List<MatchItem>
)


