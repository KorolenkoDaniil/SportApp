package com.example.sportapp.widgets.matchInfo.matchCard.specialEventsCards

import androidx.compose.runtime.Composable
import com.example.sportapp.models.soccer.api.domain.EventEntity
import com.example.sportapp.widgets.matchInfo.matchCard.specialEventsCards.playerSubstitulation.LeftSubstitulation
import com.example.sportapp.widgets.matchInfo.matchCard.specialEventsCards.playerSubstitulation.RightSubstitulation

@Composable
fun PlayerSubstitution(event: EventEntity, side: Boolean) {
    if (side) {
        LeftSubstitulation(event)
    } else {
        RightSubstitulation(event)
    }
}