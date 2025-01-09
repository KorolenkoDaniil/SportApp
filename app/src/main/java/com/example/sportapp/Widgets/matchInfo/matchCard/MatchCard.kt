package com.example.sportapp.widgets.matchInfo.matchCard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportapp.domain.EventEntity
import com.example.sportapp.domain.MatchEntity
import com.example.sportapp.widgets.matchInfo.matchCard.specialEventsCards.GoalCard
import com.example.sportapp.widgets.matchInfo.matchCard.specialEventsCards.PlayerSubstitution
import com.example.sportapp.widgets.matchInfo.matchCard.specialEventsCards.YellowCardCard

@Composable
fun MatchCard(event: EventEntity, match: MatchEntity){

    Row(
        Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            if (event.teamId == match.teamAId) {
                when (event.type) {
                    "yellow_card" ->  YellowCardCard(event, true)
                    "goal" -> GoalCard(event, true)
                    "substitution_player" -> PlayerSubstitution(event, true)
                    else -> Box { Text(event.type + "--?") }
                }

            }
        }
        Column {
            EventTimeBlock(event)
            LineImage()
        }
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            if (event.teamId == match.teamBId) {
                when (event.type) {
                    "yellow_card" ->  YellowCardCard(event, false)
                    "goal" -> GoalCard(event, false)
                    "substitution_player" -> PlayerSubstitution(event, false)
                    else -> Box { Text(event.type + "--?") }
                }
            }
        }
    }
}