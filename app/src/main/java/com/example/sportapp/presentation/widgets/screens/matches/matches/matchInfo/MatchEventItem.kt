package com.example.sportapp.presentation.widgets.screens.matches.matches.matchInfo

import androidx.compose.runtime.Composable
import com.example.sportapp.CleanArchitexture.domain.models.match.EventEntity
import com.example.sportapp.CleanArchitexture.domain.models.match.MatchEntity
import com.example.sportapp.presentation.widgets.screens.matches.matches.matchInfo.matchCard.MatchCard


@Composable
fun MatchEventItem(event: EventEntity, match: MatchEntity) {

    when (event.type) {
        "final_whistle_period" -> PhaseNameLine("2nd HALF")
        "final_whistle_match" -> PhaseNameLine("FULL TIME")
        "kick_off_period" -> {}
        else -> MatchCard(event,  match)
    }
}
