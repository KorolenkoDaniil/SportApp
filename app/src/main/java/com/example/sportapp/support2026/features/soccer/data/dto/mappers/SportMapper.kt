package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.SportDto
import com.example.sportapp.support2026.features.soccer.domain.entities.Sport

fun SportDto.toDomain(): Sport {
    return Sport(
        sportId = this.sportId ?: 0,
        sportName = this.sportName.orEmpty()
    )
}


fun Sport.toDto(): SportDto {
    return SportDto(
        sportId = this.sportId,
        sportName = this.sportName
    )
}