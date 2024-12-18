package com.example.sportapp.widgets.matches.rankigs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.domain.RankingEntity

@Composable
fun RankingCard(item: RankingEntity, index: Int) {

    val painter = rememberAsyncImagePainter(item.logoUrl)

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
            Box(
                modifier = Modifier.weight(1f)
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
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
    }

}