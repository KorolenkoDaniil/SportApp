package com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard

import androidx.annotation.OptIn
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
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.sportapp.models.soccer.api.domain.EventEntity
import com.example.sportapp.models.soccer.api.domain.MatchEntity
import com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.specialEventsCards.DoubleYellowCardCard
import com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.specialEventsCards.GoalCard
import com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.specialEventsCards.PenaltyCard
import com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.specialEventsCards.PlayerSubstitution
import com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.specialEventsCards.RedCardCard
import com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.specialEventsCards.YellowCardCard

@OptIn(UnstableApi::class)
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

                Log.d("Etypettt", event.type)
                when (event.type) {
//                     own_goal
                    "double_yellow_card" ->  DoubleYellowCardCard(event, true)
                    "penalty" ->  PenaltyCard(event, true)
                    "yellow_card" ->  YellowCardCard(event, true)
                    "red_card" ->  RedCardCard(event, true)
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
                    "double_yellow_card" ->  DoubleYellowCardCard(event, false)
                    "penalty" ->  PenaltyCard(event, false)
                    "yellow_card" ->  YellowCardCard(event, false)
                    "red_card" ->  RedCardCard(event, false)
                    "goal" -> GoalCard(event, false)
                    "substitution_player" -> PlayerSubstitution(event, false)
                    else -> Box { Text(event.type + "--?") }
                }
            }
        }
    }
}