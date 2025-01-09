package com.example.sportapp.widgets.matchInfo

import androidx.compose.runtime.Composable
import com.example.sportapp.domain.EventEntity
import com.example.sportapp.domain.MatchEntity
import com.example.sportapp.widgets.matchInfo.matchCard.MatchCard


@Composable
fun MatchEventItem(event: EventEntity, match: MatchEntity) {

    when (event.type) {
        "final_whistle_period" -> PhaseNameLine("2nd HALF")
        "final_whistle_match" -> PhaseNameLine("FULL TIME")
        "kick_off_period" -> {}
        else -> MatchCard(event,  match)
    }
}
