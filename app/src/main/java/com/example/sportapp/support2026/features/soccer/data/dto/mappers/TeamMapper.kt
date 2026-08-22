package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.TeamDto
import com.example.sportapp.support2026.features.soccer.domain.entities.Team

fun TeamDto.toDomain(): Team {
    return Team(
        teamId = this.teamId ?: 0,
        teamName = this.teamName.orEmpty(),
        shortName = this.shortName.orEmpty(),
        teamIconUrl = this.teamIconUrl.orEmpty(),
        teamGroupName = this.teamGroupName.orEmpty()
    )
}

fun Team.toDto(): TeamDto {
    return TeamDto(
        teamId = this.teamId,
        teamName = this.teamName,
        shortName = this.shortName,
        teamIconUrl = this.teamIconUrl,
        teamGroupName = this.teamGroupName
    )
}