package com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePart (date: LocalDateTime){

    val dateFormatter = DateTimeFormatter.ofPattern("MM-dd")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val formattedDate = date.format(dateFormatter)
    val formattedTime = date.format(timeFormatter)

    Column (
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = formattedDate)
        Text(text = formattedTime)
    }
}

