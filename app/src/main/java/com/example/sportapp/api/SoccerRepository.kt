package com.example.sportapp.api

import EventResponse
import android.util.Log
import com.example.sportapp.api.entities.matchReport.MatchReportResponse
import com.example.sportapp.api.entities.matches.MatchItem
import com.example.sportapp.api.entities.matches.MatchResponse
import com.example.sportapp.api.entities.ranking.TeamResponse
import com.example.sportapp.api.Mappers.RankingsMapper
import com.example.sportapp.domain.EventResponseEntity
import com.example.sportapp.domain.MatchDayEntity
import com.example.sportapp.domain.MatchEntity
import com.example.sportapp.domain.RankingEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.path
import kotlinx.serialization.json.Json
import java.time.ZonedDateTime


class SoccerRepository {

    private val baseUrl = "https://dev-lsa-stats.origins-digital.com/lsa/stats/api/proxy/d3/"
    private val seasonId = "serie-a::Football_Season::1e32f55e98fc408a9d1fc27c0ba43243"

    private val rankingsMapper = RankingsMapper()


    private val json = Json {
        ignoreUnknownKeys = true // Игнорирует неизвестные поля
    }

    private val client = HttpClient {
        defaultRequest {
            this.url(baseUrl)
        }
    }

    private val client1 = HttpClient {
        defaultRequest {
            this.url(baseUrl)
        }
    }


    suspend fun getMatchDays(): List<MatchDayEntity> {

        val builder = HttpRequestBuilder()
        builder.method = HttpMethod.Get
        builder.url {
            this.path("calendar")
            this.parameters.append("season_id", seasonId)
        }


        val response = client.request(builder)

        val responseString: String = response.body()

        Log.d("ttt", responseString)

        val matchResponse: MatchResponse = json.decodeFromString(responseString)

        val matches = matchResponse.items.orEmpty()

        return matchDaysList(matches)
    }


    private fun matchDaysList(matches: List<MatchItem>): List<MatchDayEntity> {
        val group = matches.groupBy { it.matchDayName }

        Log.d("ttt", group.toString())

        return group.keys.sortedBy {
            it.substringAfter("Matchday ").toIntOrNull()
        }.map { name ->
            MatchDayEntity(name = name,
                matches = group[name].orEmpty().map { item ->
                    MatchEntity(
                        id = item.matchId,
                        date = ZonedDateTime.parse(item.date).toLocalDateTime(),
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
                    )
                }
            )
        }

    }


    suspend fun getRankings(): List<RankingEntity> {

        val builder = HttpRequestBuilder()
        builder.method = HttpMethod.Get

        builder.url {
            this.path("rankings")
            this.parameters.append("season_id", seasonId)
        }

        val response = client1.request(builder)

        val responseString: String = response.body()

        Log.d("ttt", responseString)

        val teamResponse: TeamResponse = json.decodeFromString(responseString)
        return rankingsMapper.mapRankings(teamResponse)

    }


    suspend fun getMatchReport(matchId: String): List<EventResponseEntity> {
        val builder = HttpRequestBuilder()
        builder.method = HttpMethod.Get

        builder.url {
            this.path("match_report")
            this.parameters.append("season_id", seasonId)
            this.parameters.append("match_id", matchId)
        }

        val response = client1.request(builder)

        val responseString: String = response.body()

        Log.d("tttMatchReport", responseString)

        val match: MatchReportResponse = json.decodeFromString(responseString)

        val matchEvents =  match.items

        return getMatchEventsList(matchEvents)
    }


    private fun getMatchEventsList(matchEvents: List<EventResponse>): List<EventResponseEntity> {
        return matchEvents.map { event ->
            EventResponseEntity(
                type = event.type ?: "unknown",
                matchPhase = event.matchPhase ?: "default_phase",
                order = event.order?: 0,
                id = event.id ?: "0",
                teamId = event.teamId ?: "0",
                playerId = event.playerId ?: "0",
                playerSurname = event.playerSurname ?: "Unknown",
                name = event.name ?: "Unknown",
                playerFullName = event.playerFullName ?: "Unknown",
                playerShirtName = event.playerShirtName ?: "Unknown",
                minute = event.minute ?: 0
            )
        }
    }

}