package com.example.sportapp.models.soccer.api.mappers

import android.util.Log
import com.example.sportapp.models.soccer.domain.MatchDayEntity
import com.example.sportapp.models.soccer.domain.MatchEntity
import com.example.sportapp.models.soccer.api.entities.matches.MatchItem

class MatchDaysMapper {
    fun getMatchDaysList(matches: List<MatchItem>): List<MatchDayEntity> {

        val baseUrl = "https://origins-lsa.origins-digital.com/assets/deltatre/teams/"

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
                        logoUrlA = "$baseUrl${item.teamAId}.png",
                        logoUrlB = "$baseUrl${item.teamBId}.png",
                        localDateTimeMatchStart = item.convertStringToLocalDate()
                    )
                }
            )
        }
    }
}