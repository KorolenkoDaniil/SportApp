package com.example.sportapp.pages.widgets.matches.matchesList.matchesCard.cardParts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DatePart(modifier: Modifier, date: ZonedDateTime) {

    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val formattedDate = date.format(dateFormatter)
    val formattedTime = date.format(timeFormatter)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = formattedDate)
        Text(text = formattedTime)
    }
}

