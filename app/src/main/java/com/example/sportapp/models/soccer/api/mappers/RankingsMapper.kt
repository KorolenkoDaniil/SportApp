package com.example.sportapp.models.soccer.api.mappers

import com.example.sportapp.models.soccer.api.domain.RankingEntity
import com.example.sportapp.models.soccer.api.entities.ranking.TeamResponse

class RankingsMapper {

    fun getListRankingEntity (response: TeamResponse) : List<RankingEntity> {
        return response.items.map { item ->
            RankingEntity(
                id = item.teamId,
                name = item.shortName,
                logoUrl = "https://origins-lsa.origins-digital.com/assets/deltatre/teams/${item.teamId}.png",
                fullName = item.fullName,
                shortName = item.shortName,
                teamId = item.teamId,
                ranking = item.ranking,
                points = item.points,
                gamesPlayed = item.gamesPlayed,
                won = item.won,
                draws = item.draws,
                lost = item.lost,
                goalsMade = item.goalsMade,
                goalsConceeded = item.goalsConceeded,
                pointsHome = item.pointsHome,
                playedHome = item.playedHome,
                winHome = item.winHome,
                drawsHome = item.drawsHome,
                lostHome = item.lostHome,
                goalsMadeHome = item.goalsMadeHome,
                goalsConceededHome = item.goalsConceededHome,
                pointsAway = item.pointsAway,
                playedAway = item.playedAway,
                wonAway = item.wonAway,
                drawsAway = item.drawsAway,
                lostAway = item.lostAway,
                goalsMadeAway = item.goalsMadeAway,
                goalsConceededAway = item.goalsConceededAway,
                lastMatchesResults = item.lastMatchesResults
            )
        }
    }
}