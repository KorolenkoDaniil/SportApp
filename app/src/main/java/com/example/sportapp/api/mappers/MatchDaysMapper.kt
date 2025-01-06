package com.example.sportapp.api.mappers

import android.util.Log
import com.example.sportapp.api.entities.matches.MatchItem
import com.example.sportapp.domain.MatchDayEntity
import com.example.sportapp.domain.MatchEntity

class MatchDaysMapper {
    fun matchDaysList(matches: List<MatchItem>): List<MatchDayEntity> {
        val group = matches.groupBy { it.matchDayName }

        Log.d("ttt", group.toString())

        return group.keys.sortedBy {
            it.substringAfter("Matchday ").toIntOrNull()
        }.map { name ->
            MatchDayEntity(name = name,
                matches = group[name].orEmpty().map { item ->
                    MatchEntity(
                        id = item.matchId,
                        date = item.date,
                        competitionId = item.competitionId,
                        seasonId = item.seasonId,
                        matchId = item.matchId,
                        matchUniqueCode = item.matchUniqueCode,
                        matchDayId = item.matchDayId,
                        matchDayName = item.matchDayName,
                        matchDayShortName = item.matchDayShortName,
                        dateOrder = item.dateOrder,
                        teamAId = item.teamAId,
                        teamAName = item.teamAName,
                        teamAShortName = item.teamAShortName,
                        teamAAcronym = item.teamAAcronym,
                        teamBId = item.teamBId,
                        teamBName = item.teamBName,
                        teamBShortName = item.teamBShortName,
                        teamBAcronym = item.teamBAcronym,
                        goalsTeamA = item.goalsTeamA,
                        goalsTeamB = item.goalsTeamB,
                        stadiumName = item.stadiumName,
                        stadiumCity = item.stadiumCity,
                        matchStatus = item.matchStatus,
                        isAbandoned = item.isAbandoned,
                        isPostponed = item.isPostponed,
                        isSuspended = item.isSuspended,
                        broadcaster = item.broadcaster,
                        matchStartTime = item.matchStartTime,
                        matchPhase = item.matchPhase,
                        optaId = item.optaId,
                        isForfeitWin = item.isForfeitWin,
                        minute = item.minute,
                        logoUrlA = "https://origins-lsa.origins-digital.com/assets/deltatre/teams/${item.teamAId}.png",
                        logoUrlB = "https://origins-lsa.origins-digital.com/assets/deltatre/teams/${item.teamBId}.png",
                        localDateTimeMatchStart = item.convertStringToLocalDate()
                    )
                }
            )
        }
    }
}