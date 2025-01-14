package com.example.sportapp.models.soccer

import android.util.Log
import com.example.sportapp.models.soccer.api.entities.matchReport.MatchReportResponse
import com.example.sportapp.models.soccer.api.entities.matches.MatchResponse
import com.example.sportapp.models.soccer.api.entities.ranking.TeamResponse
import com.example.sportapp.models.soccer.api.mappers.MatchDaysMapper
import com.example.sportapp.models.soccer.api.mappers.MatchMapper
import com.example.sportapp.models.soccer.api.mappers.MatchReportMapper
import com.example.sportapp.models.soccer.api.mappers.RankingsMapper
import com.example.sportapp.models.soccer.api.domain.EventEntity
import com.example.sportapp.models.soccer.api.domain.MatchDayEntity
import com.example.sportapp.models.soccer.api.domain.MatchEntity
import com.example.sportapp.models.soccer.api.domain.RankingEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.path
import kotlinx.serialization.json.Json


class SoccerRepository {

    //ссылка доступа
    private val baseUrl = "https://dev-lsa-stats.origins-digital.com/lsa/stats/api/proxy/d3/"
    private val seasonId = "serie-a::Football_Season::1e32f55e98fc408a9d1fc27c0ba43243"

    //мапперы для доступа к вынесенным методам
    private val rankingsMapper = RankingsMapper()
    private val matchReportMapper = MatchReportMapper()
    private val matchDaysMapper = MatchDaysMapper()
    private val matchMapper = MatchMapper()


    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val client = HttpClient {
        defaultRequest {
            this.url(baseUrl)
        }
    }


    suspend fun getMatch (matchId: String): MatchEntity {
        val builder = HttpRequestBuilder()
        builder.method = HttpMethod.Get

        builder.url {
            this.path("calendar")
            this.parameters.append("season_id", seasonId)
            this.parameters.append("match_id", matchId)
        }

        val response = client.request(builder)

        val responseString: String = response.body()

        Log.d("tttOneMatch", responseString)

        val matchResponse: MatchResponse = json.decodeFromString(responseString)

        return matchMapper.getMatch(matchResponse.items!![0])
    }




    suspend fun getMatchDays(): List<MatchDayEntity> {

        val builder = HttpRequestBuilder()
        builder.method = HttpMethod.Get
        builder.url {
            this.path("calendar")
            this.parameters.append("season_id", seasonId)
        }


//        https://dev-lsa-stats.origins-digital.com/lsa/stats/api/proxy/d3/calendar?season_id=serie-a%3A%3AFootball_Season%3A%3A1e32f55e98fc408a9d1fc27c0ba43243

        val response = client.request(builder)

        val responseString: String = response.body()

        Log.d("ttt", responseString)

        val matchResponse: MatchResponse = json.decodeFromString(responseString)

        val matches = matchResponse.items.orEmpty()

        return matchDaysMapper.getMatchDaysList(matches)
    }



    suspend fun getRankings(): List<RankingEntity> {

        val builder = HttpRequestBuilder()
        builder.method = HttpMethod.Get

        builder.url {
            this.path("rankings")
            this.parameters.append("season_id", seasonId)
        }

        val response = client.request(builder)

        val responseString: String = response.body()

        Log.d("ttt", responseString)

        val teamResponse: TeamResponse = json.decodeFromString(responseString)
        return rankingsMapper.getListRankingEntity(teamResponse)

    }


    suspend fun getMatchReport(matchId: String): List<EventEntity> {
        val builder = HttpRequestBuilder()
        builder.method = HttpMethod.Get

        val url = builder.url {
            this.path("match_report")
            this.parameters.append("season_id", seasonId)
            this.parameters.append("match_id", matchId)
        }

        Log.d("URL", url.toString())


        val response = client.request(builder)

        val responseString: String = response.body()

        Log.d("tttMatchReport", responseString)

        val match: MatchReportResponse = json.decodeFromString(responseString)

        val matchEvents =  match.items

        return matchReportMapper.getMatchEventsList(matchEvents)
    }

}