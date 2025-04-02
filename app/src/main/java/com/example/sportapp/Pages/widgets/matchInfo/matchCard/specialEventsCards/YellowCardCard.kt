package com.example.sportapp.pages.widgets.matchInfo.matchCard.specialEventsCards

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import com.example.sportapp.models.soccer.api.domain.EventEntity

@Composable
fun YellowCardCard (event: EventEntity, side: Boolean) {
    if (side) {
        LeftLeaf(
            event = event,
            iconId = R.drawable.yellowcard,
            iconSize = 20.dp
        )
    }
    else {
        RightLeaf(
            event = event,
            iconId = R.drawable.yellowcard,
            iconSize = 20.dp
        )
    }
}