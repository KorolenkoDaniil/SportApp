package com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.specialEventsCards

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import com.example.sportapp.models.soccer.api.domain.EventEntity

@Composable
fun GoalCard (event: EventEntity, side: Boolean) {
    if (side) {
        LeftLeaf(
            event = event,
            iconId = R.drawable.ball,
            iconSize = 15.dp
        )
    }
    else {
        RightLeaf(
            event = event,
            iconId = R.drawable.ball,
            iconSize = 15.dp
        )
    }
}