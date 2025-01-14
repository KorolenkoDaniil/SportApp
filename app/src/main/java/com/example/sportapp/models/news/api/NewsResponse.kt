package com.example.sportapp.models.news.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class NewsResponse(
    @SerialName("dateTime") val dateTime: String,
    @SerialName("sport") val sport: String,
    @SerialName("text") val text: String,
) {

    fun convertStringToLocalDate(): ZonedDateTime {

        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val localZoneId: ZoneId = ZoneId.systemDefault()

        val zonedDateTime = ZonedDateTime.parse(dateTime, formatter)
        return zonedDateTime.withZoneSameInstant(localZoneId)
    }
}