package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.BlTableTeamDto
import com.example.sportapp.support2026.features.soccer.domain.entities.BlTableTeam

fun BlTableTeamDto.toDomain(): BlTableTeam {
    return BlTableTeam(
        teamInfoId = this.teamInfoId ?: 0,
        teamName = this.teamName.orEmpty(),
        shortName = this.shortName.orEmpty(),
        teamIconUrl = this.teamIconUrl.orEmpty(),
        points = this.points ?: 0,
        opponentGoals = this.opponentGoals ?: 0,
        goals = this.goals ?: 0,
        matches = this.matches ?: 0,
        won = this.won ?: 0,
        lost = this.lost ?: 0,
        draw = this.draw ?: 0,
        goalDiff = this.goalDiff ?: 0
    )
}

fun BlTableTeam.toDto(): BlTableTeamDto {
    return BlTableTeamDto(
        teamInfoId = this.teamInfoId,
        teamName = this.teamName,
        shortName = this.shortName,
        teamIconUrl = this.teamIconUrl,
        points = this.points,
        opponentGoals = this.opponentGoals,
        goals = this.goals,
        matches = this.matches,
        won = this.won,
        lost = this.lost,
        draw = this.draw,
        goalDiff = this.goalDiff
    )
}