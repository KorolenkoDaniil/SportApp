package com.example.sportapp.widgets.matches.matchesList.matchesCard

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
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.ImagePart
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.TextPart

@Composable
fun MatchCard(teamA: String, teamB: String, date: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CardPart(modifier = Modifier.weight(1f), TextPart(teamA))
                CardPart(modifier = Modifier.weight(1f), ImagePart())
                CardPart(modifier = Modifier.weight(1f), TextPart(date))
                CardPart(modifier = Modifier.weight(1f), ImagePart())
                CardPart(modifier = Modifier.weight(1f), TextPart(teamB))
            }
        }
    }
}