package com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DatePart(modifier: Modifier, date: String) {

    val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
    val zonedDateTime = ZonedDateTime.parse(date, formatter)

    val localZoneId = ZoneId.systemDefault()
    val localTime = zonedDateTime.withZoneSameInstant(localZoneId)

    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val formattedDate = localTime.format(dateFormatter)
    val formattedTime = localTime.format(timeFormatter)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = formattedDate)
        Text(text = formattedTime)
    }
}

