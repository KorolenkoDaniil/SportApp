package com.example.sportapp.API.entities

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class MatchResponse(
    @SerialName("page_index") val pageIndex: Int? = null,
    @SerialName("page_size") val pageSize: Int? = null,
    @SerialName("items_count") val itemsCount: Int? = null,
    @SerialName("is_last_page") val isLastPage: Boolean? = null,
    @SerialName("items") val items: List<MatchItem>? = null
)


suspend fun fetchMatches(): List<MatchItem>? {

    val GET_UUID =
        "https://dev-lsa-stats.origins-digital.com/lsa/stats/api/proxy/d3/calendar?season_id=serie-a%3A%3AFootball_Season%3A%3A1e32f55e98fc408a9d1fc27c0ba43243"

    val client = HttpClient()

    try {
        val response: HttpResponse = client.get(GET_UUID)
        val data: String = response.body()

        val matchResponse: MatchResponse = Json.decodeFromString(data)

        return matchResponse.items

    } catch (e: Exception) {
        Log.e("FetchMatches", "Error: ${e.message}")
        return null
    } finally {
        client.close()
    }
}





