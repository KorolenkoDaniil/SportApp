package com.example.sportapp.widgets.matches.rankigs.rankingsParts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.models.soccer.api.domain.RankingEntity

@Composable
fun TeamPart (modifier: Modifier, item: RankingEntity, index: Int){

    val painter = rememberAsyncImagePainter(item.logoUrl)

    Box(
        modifier = modifier
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text((index + 1).toString(), modifier = Modifier.padding(end = 8.dp))
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )
            Text(item.shortName, modifier = Modifier.padding(end = 8.dp))
        }
    }
}