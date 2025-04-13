package com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.specialEventsCards

import androidx.compose.runtime.Composable
import com.example.sportapp.CleanArchitexture.domain.models.match.EventEntity
import com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.specialEventsCards.playerSubstitulation.LeftSubstitulation
import com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.specialEventsCards.playerSubstitulation.RightSubstitulation

@Composable
fun PlayerSubstitution(event: EventEntity, side: Boolean) {
    if (side) {
        LeftSubstitulation(event)
    } else {
        RightSubstitulation(event)
    }
}