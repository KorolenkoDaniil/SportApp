package com.example.sportapp.widgets.matches.matchesList.matchesCard

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.DatePart
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.ImagePart
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.TextPart
import java.time.ZonedDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MatchCard(
    teamA: String,
    teamB: String,
    isoDate: String,
    logoURLA: String?,
    logoURLB: String?,
) {

    Log.d("MatchCard", "teamA: $teamA, teamB: $teamB, logoURLA: $logoURLA, logoURLB: $logoURLB")

    val date = ZonedDateTime.parse(isoDate).toLocalDateTime()

    Box(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .fillMaxSize(),
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CardPart(modifier = Modifier.weight(1f), TextPart(teamA))
                    CardPart(modifier = Modifier.weight(1f), ImagePart(logoURLA))
                    CardPart(modifier = Modifier.weight(1f), DatePart(date))
                    CardPart(modifier = Modifier.weight(1f), ImagePart(logoURLB))
                    CardPart(modifier = Modifier.weight(1f), TextPart(teamB))
                }
            }
        }
    }
}