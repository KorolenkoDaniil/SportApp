package com.example.sportapp.widgets.matchInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.domain.MatchEntity
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.DatePart
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.ScorePart
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Composable
fun MatchCardContent(matchDay: MatchEntity?, currentSize: Dp) {

    val painterLogoA = rememberAsyncImagePainter(matchDay?.logoUrlA)
    val painterLogoB = rememberAsyncImagePainter(matchDay?.logoUrlB)

    val currentTime = ZonedDateTime.now().toLocalDateTime()
    val date = LocalDateTime.parse(matchDay!!.date)


    Row(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterLogoA,
                contentDescription = null,
                modifier = Modifier
                    .size(currentSize)
            )

            Text(text = matchDay.teamAName)
        }




        if (date.isBefore(currentTime) || date.isEqual(currentTime)) {
            ScorePart(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(), matchDay
            )
        }
        if (date.isAfter(currentTime)) {
            DatePart(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(), date = matchDay.date
            )
        }




        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterLogoB,
                contentDescription = null,
                modifier = Modifier
                    .size(currentSize)
            )

            Text(text = matchDay.teamBName)
        }
    }
}