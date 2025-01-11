package com.example.sportapp.widgets.matchInfo.matchCard.specialEventsCards

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import com.example.sportapp.domain.EventEntity

@Composable
fun RedCardCard (event: EventEntity, side: Boolean) {
    if (side) {
        LeftLeaf(
            event = event,
            iconId = R.drawable.redcard,
            iconSize = 20.dp
        )
    }
    else {
        RightLeaf(
            event = event,
            iconId = R.drawable.redcard,
            iconSize = 20.dp
        )
    }
}