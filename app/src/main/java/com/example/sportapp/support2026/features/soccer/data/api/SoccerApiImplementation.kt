package com.example.sportapp.support2026.features.soccer.data.api

import android.util.Log
import com.example.sportapp.support2026.app.openLigaBbBaseUrl
import com.example.sportapp.support2026.features.soccer.data.dto.MatchDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class SoccerApiImplementation @Inject constructor(
    private val client: HttpClient
) : SoccerApi {

    override suspend fun getMatchData(matchId: Int): MatchDto {
        Log.d("getMatchData", "$matchId")
        val matchData: MatchDto = client
            .get("$openLigaBbBaseUrl/getmatchdata/$matchId")
            .body()
        Log.d("getMatchData", "$matchData")
        return matchData
    }

    override suspend fun getLeagueMatches(): List<MatchDto> {

        val matchesData: List<MatchDto> = client.get("$openLigaBbBaseUrl/getmatchdata/bl1/2026").body()
        Log.d("getLeagueMatchData", "$matchesData")
        return matchesData
    }


}