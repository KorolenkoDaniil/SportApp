package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.MatchResultDto
import com.example.sportapp.support2026.features.soccer.domain.entities.MatchResult

fun MatchResultDto.toDomain(): MatchResult {
    return MatchResult (
        resultID = this.resultID ?: 0,
        resultName = this.resultName.orEmpty(),
        pointsTeam1 = this.pointsTeam1 ?: 0,
        pointsTeam2 = this.pointsTeam2 ?: 0,
        resultOrderID = this.resultOrderID ?: 0,
        resultTypeID = this.resultTypeID ?: 0,
        resultTypeKind = this.resultTypeKind.orEmpty(),
        resultDescription = this.resultDescription.orEmpty()
    )
}

fun MatchResult.toDto(): MatchResultDto {
    return MatchResultDto(
        resultID = this.resultID,
        resultName = this.resultName,
        pointsTeam1 = this.pointsTeam1,
        pointsTeam2 = this.pointsTeam2,
        resultOrderID = this.resultOrderID,
        resultTypeID = this.resultTypeID,
        resultTypeKind = this.resultTypeKind,
        resultDescription = this.resultDescription
    )
}

fun List<MatchResultDto>.toDomain(): List<MatchResult> {
    return this.map { it.toDomain() }
}

fun List<MatchResult>.toDto(): List<MatchResultDto> {
    return this.map { it.toDto() }
}