package com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo

import androidx.compose.runtime.Composable
import com.example.sportapp.models.soccer.api.domain.EventEntity
import com.example.sportapp.models.soccer.api.domain.MatchEntity
import com.example.sportapp.pagesAndWidgets.widgets.matches.matchInfo.matchCard.MatchCard


@Composable
fun MatchEventItem(event: EventEntity, match: MatchEntity) {

    when (event.type) {
        "final_whistle_period" -> PhaseNameLine("2nd HALF")
        "final_whistle_match" -> PhaseNameLine("FULL TIME")
        "kick_off_period" -> {}
        else -> MatchCard(event,  match)
    }
}
