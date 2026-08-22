package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.MatchDto
import com.example.sportapp.support2026.features.soccer.domain.entities.Match

fun MatchDto.toDomain(): Match {
    return Match(
        matchID = this.matchID ?: 0,
        matchDateTime = this.matchDateTime.orEmpty(),
        timeZoneID = this.timeZoneID.orEmpty(),
        leagueId = this.leagueId ?: 0,
        leagueName = this.leagueName.orEmpty(),
        leagueSeason = this.leagueSeason ?: 0,
        leagueShortcut = this.leagueShortcut.orEmpty(),
        matchDateTimeUTC = this.matchDateTimeUTC.orEmpty(),
        group = this.group?.toDomain(),
        team1 = this.team1?.toDomain(),
        team2 = this.team2?.toDomain(),
        lastUpdateDateTime = this.lastUpdateDateTime.orEmpty(),
        matchIsFinished = this.matchIsFinished ?: false,
        matchResults = this.matchResults?.map { it.toDomain() } ?: emptyList(),
        goals = this.goals?.map { it.toDomain() } ?: emptyList(),
        location = this.location?.toDomain(),
        numberOfViewers = this.numberOfViewers ?: 0
    )
}


fun Match.toDto(): MatchDto {
    return MatchDto(
        matchID = this.matchID,
        matchDateTime = this.matchDateTime,
        timeZoneID = this.timeZoneID,
        leagueId = this.leagueId,
        leagueName = this.leagueName,
        leagueSeason = this.leagueSeason,
        leagueShortcut = this.leagueShortcut,
        matchDateTimeUTC = this.matchDateTimeUTC,
        group = this.group?.toDto(),
        team1 = this.team1?.toDto(),
        team2 = this.team2?.toDto(),
        lastUpdateDateTime = this.lastUpdateDateTime,
        matchIsFinished = this.matchIsFinished,
        matchResults = this.matchResults.map { it.toDto() },
        goals = this.goals.map { it.toDto() },
        location = this.location?.toDto(),
        numberOfViewers = this.numberOfViewers
    )
}

fun List<MatchDto>?.toDomain(): List<Match>? {
    return this?.map { it.toDomain() }
}

fun List<Match>.toDto(): List<MatchDto> {
    return this.map { it.toDto() }
}