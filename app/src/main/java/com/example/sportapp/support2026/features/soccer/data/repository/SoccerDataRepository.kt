package com.example.sportapp.support2026.features.soccer.data.repository

import com.example.sportapp.support2026.features.soccer.data.api.SoccerApi
import com.example.sportapp.support2026.features.soccer.data.dto.MatchDto
import com.example.sportapp.support2026.features.soccer.data.dto.mappers.toDomain
import com.example.sportapp.support2026.features.soccer.domain.entities.Match
import com.example.sportapp.support2026.features.soccer.domain.repository.SoccerDomainRepository
import javax.inject.Inject


class SoccerDataRepository @Inject constructor(
    private val api: SoccerApi
) : SoccerDomainRepository {

    override suspend fun getMatchData(matchId: Int): Match {
        val matchData: MatchDto = api
            .getMatchData(matchId)

        return matchData.toDomain()
    }

    override suspend fun getLeagueMatches(): List<Match>? {

        val matchDataList: List<MatchDto> = api
            .getLeagueMatches()

        return matchDataList.toDomain()
    }

}