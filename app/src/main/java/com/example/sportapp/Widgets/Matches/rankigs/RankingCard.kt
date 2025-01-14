package com.example.sportapp.widgets.matches.rankigs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.soccer.api.domain.RankingEntity
import com.example.sportapp.widgets.matches.rankigs.rankingsParts.ScorePart
import com.example.sportapp.widgets.matches.rankigs.rankingsParts.TeamPart

@Composable
fun RankingCard(item: RankingEntity, index: Int, color: Color) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.fillMaxHeight().width(7.dp).background(color = color))

            TeamPart(Modifier.weight(1f), item, index)

            ScorePart(modifier = Modifier.weight(1f), item)
        }
    }

}