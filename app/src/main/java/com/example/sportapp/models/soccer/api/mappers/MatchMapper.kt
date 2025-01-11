package com.example.sportapp.models.soccer.api.mappers

import com.example.sportapp.models.soccer.domain.MatchEntity
import com.example.sportapp.models.soccer.api.entities.matches.MatchItem

class MatchMapper {

    fun getMatch (match: MatchItem) : MatchEntity {
        return MatchEntity(
            id = match.matchId,
            date = match.date,
            localDateTimeMatchStart = match.convertStringToLocalDate(),
            competitionId = match.competitionId,
            seasonId = match.seasonId,
            matchId = match.matchId,
            matchUniqueCode = match.matchUniqueCode,
            matchDayId = match.matchDayId,
            matchDayName = match.matchDayName,
            matchDayShortName = match.matchDayShortName,
            dateOrder = match.dateOrder,
            teamAId = match.teamAId,
            teamAName = match.teamAName,
            teamAShortName = match.teamAShortName,
            teamAAcronym = match.teamAAcronym,
            teamBId = match.teamBId,
            teamBName = match.teamBName,
            teamBShortName = match.teamBShortName,
            teamBAcronym = match.teamBAcronym,
            goalsTeamA = match.goalsTeamA,
            goalsTeamB = match.goalsTeamB,
            stadiumName = match.stadiumName,
            stadiumCity = match.stadiumCity,
            matchStatus = match.matchStatus,
            isAbandoned = match.isAbandoned,
            isPostponed = match.isPostponed,
            isSuspended = match.isSuspended,
            broadcaster = match.broadcaster,
            matchStartTime = match.matchStartTime,
            matchPhase = match.matchPhase,
            optaId = match.optaId,
            isForfeitWin = match.isForfeitWin,
            minute = match.minute,
            logoUrlA = "https://origins-lsa.origins-digital.com/assets/deltatre/teams/${match.teamAId}.png",
            logoUrlB = "https://origins-lsa.origins-digital.com/assets/deltatre/teams/${match.teamBId}.png",
        )
    }
}