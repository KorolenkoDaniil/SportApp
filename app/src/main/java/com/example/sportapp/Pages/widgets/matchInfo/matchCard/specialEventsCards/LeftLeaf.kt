package com.example.sportapp.pages.widgets.matchInfo.matchCard.specialEventsCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.soccer.api.domain.EventEntity

@Composable
fun LeftLeaf (event: EventEntity, iconId: Int, iconSize: Dp){
    Row(Modifier.padding(end = 12.dp)) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(40.dp)
                .shadow(4.dp, shape = RectangleShape)
                .background(color = Color.White)
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                Column(
                    Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(event.playerFullName, modifier = Modifier.padding(bottom = 4.dp))

                }

                Image(
                    painter = painterResource(iconId),
                    contentDescription = "",
                    modifier = Modifier
                        .size(iconSize)
                        .align(Alignment.CenterVertically),
                )
            }
        }
    }
}