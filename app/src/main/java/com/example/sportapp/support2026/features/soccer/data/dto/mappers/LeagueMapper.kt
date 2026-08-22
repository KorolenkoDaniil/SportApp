package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.LeagueDto
import com.example.sportapp.support2026.features.soccer.domain.entities.League

fun LeagueDto.toDomain(): League {
    return League(
        leagueId = this.leagueId ?: 0,
        leagueName = this.leagueName.orEmpty(),
        leagueShortcut = this.leagueShortcut.orEmpty(),
        leagueSeason = this.leagueSeason.orEmpty(),
        sport = this.sport?.toDomain()
    )
}

fun League.toDto(): LeagueDto {
    return LeagueDto(
        leagueId = this.leagueId,
        leagueName = this.leagueName,
        leagueShortcut = this.leagueShortcut,
        leagueSeason = this.leagueSeason,
        sport = this.sport?.toDto()
    )
}