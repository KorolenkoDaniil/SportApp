package com.example.sportapp.api

import android.util.Log
import com.example.sportapp.api.entities.Ranking.TeamResponse
import com.example.sportapp.domain.MatchDayEntity
import com.example.sportapp.api.entities.matches.MatchItem
import com.example.sportapp.api.entities.matches.MatchResponse
import com.example.sportapp.domain.RankingEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.path
import kotlinx.serialization.json.Json

class SoccerRepository {

    private val baseUrl = "https://dev-lsa-stats.origins-digital.com/lsa/stats/api/proxy/d3/"
    private val seasonId = "serie-a::Football_Season::1e32f55e98fc408a9d1fc27c0ba43243"

    private val json = Json


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

        val matchResponse: MatchResponse = json.decodeFromString(responseString)

        val matches = matchResponse.items.orEmpty()

        return matchDaysList(matches)
    }


    private fun matchDaysList(matches: List<MatchItem>): List<MatchDayEntity> {
        val group = matches.groupBy { it.matchDayName }

        Log.d("group", group.toString())

        return group.keys.sortedBy {
            it.substringAfter("Matchday ").toIntOrNull()
        }.map { name ->
            MatchDayEntity(name = name, matches = group[name].orEmpty())
        }.also {
            Log.d("ttt1", it.toString())
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

        Log.d("rankings", responseString)

        val teamResponse: TeamResponse = json.decodeFromString(responseString)

        return teamResponse.items.map { item ->
            RankingEntity(
                id = item.teamId,
                name = item.shortName,
                logoUrl = "https://origins-lsa.origins-digital.com/assets/deltatre/teams/${item.teamId}.png"
            )
        }

        /*val matches = matchResponse.items.orEmpty()*/

//        return matchDaysList(matches)
    }


}