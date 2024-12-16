package com.example.sportapp.domain

import java.time.LocalDateTime

data class MatchDayEntity(
    val name: String,
    val matches: List<MatchEntity>,
)

data class MatchEntity(
    val id: String,
    val date: LocalDateTime,
    val competitionId: String,
    val seasonId: String,
    val matchId: String,
    val matchUniqueCode: String,
    val matchDayId: String,
    val matchDayName: String,
    val matchDayShortName: String,
    val dateOrder: String,
    val teamAId: String,
    val teamAName: String,
    val teamAShortName: String,
    val teamAAcronym: String,
    val teamBId: String,
    val teamBName: String,
    val teamBShortName: String,
    val teamBAcronym: String,
    val goalsTeamA: Int? = null,
    val goalsTeamB: Int? = null,
    val stadiumName: String,
    val stadiumCity: String,
    val matchStatus: Int,
    val isAbandoned: Int,
    val isPostponed: Int,
    val isSuspended: Int,
    val broadcaster: String? = null,
    val matchStartTime: String,
    val matchPhase: String,
    val optaId: String,
    val isForfeitWin: Int,
    val minute: Int?,

    )