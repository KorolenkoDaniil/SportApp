package com.example.sportapp.API.Entities

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class MatchResponse(
    @SerialName("page_index") val pageIndex: Int,
    @SerialName("page_size") val pageSize: Int,
    @SerialName("items_count") val itemsCount: Int,
    @SerialName("is_last_page") val isLastPage: Boolean,
    @SerialName("items") val items: List<MatchItem>
)

@Serializable
data class MatchItem(
    @SerialName("competition_id") val competitionId: String,
    @SerialName("season_id") val seasonId: String,
    @SerialName("match_id") val matchId: String,
    @SerialName("match_unique_code") val matchUniqueCode: String,
    @SerialName("date") val date: String,
    @SerialName("match_day_id") val matchDayId: String,
    @SerialName("match_day_name") val matchDayName: String,
    @SerialName("match_day_short_name") val matchDayShortName: String,
    @SerialName("date_order") val dateOrder: String,
    @SerialName("team_a_id") val teamAId: String,
    @SerialName("team_a_name") val teamAName: String,
    @SerialName("team_a_short_name") val teamAShortName: String,
    @SerialName("team_a_acronym") val teamAAcronym: String,
    @SerialName("team_b_id") val teamBId: String,
    @SerialName("team_b_name") val teamBName: String,
    @SerialName("team_b_short_name") val teamBShortName: String,
    @SerialName("team_b_acronym") val teamBAcronym: String,
    @SerialName("goals_team_a") val goalsTeamA: Int? = null,
    @SerialName("goals_team_b") val goalsTeamB: Int? = null,
    @SerialName("stadium_name") val stadiumName: String,
    @SerialName("stadium_city") val stadiumCity: String,
    @SerialName("match_status") val matchStatus: Int,
    @SerialName("is_abandoned") val isAbandoned: Int,
    @SerialName("is_postponed") val isPostponed: Int,
    @SerialName("is_suspended") val isSuspended: Int,
    @SerialName("broadcaster") val broadcaster: String? = null,
    @SerialName("match_start_time") val matchStartTime: String,
    @SerialName("match_phase") val matchPhase: String,
    @SerialName("opta_id") val optaId: String,
    @SerialName("is_forfeit_win") val isForfeitWin: Int,
    @SerialName("minute") val minute: Int
)


suspend fun fetchMatches() {

    val GET_UUID =
        "https://dev-lsa-stats.origins-digital.com/lsa/stats/api/proxy/d3/calendar?season_id=serie-a%3A%3AFootball_Season%3A%3A1e32f55e98fc408a9d1fc27c0ba43243"

    val client = HttpClient()

    try {
        val response: HttpResponse = client.get(GET_UUID)
        val data: String = response.body()
        Log.i("FetchMatches", "Response: $data")

        val matchResponse: MatchResponse = Json.decodeFromString(data)

        Log.d("ttt", matchResponse.itemsCount.toString())
    } catch (e: Exception) {
        Log.e("FetchMatches", "Error: ${e.message}")
    } finally {
        client.close()
    }
}

