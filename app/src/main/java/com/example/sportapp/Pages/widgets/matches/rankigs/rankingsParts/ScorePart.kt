package com.example.sportapp.pages.widgets.matches.rankigs.rankingsParts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sportapp.models.soccer.api.domain.RankingEntity

@Composable
fun ScorePart (modifier: Modifier,item: RankingEntity) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = item.points.toString())
        Text(text = item.gamesPlayed .toString())
        Text(text = item.won.toString())
        Text(text = item.draws.toString())
        Text(text = item.lost.toString())
        Text(text = (item.goalsMade - item.goalsConceeded).toString())
    }
}