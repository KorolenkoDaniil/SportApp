package com.example.sportapp.widgets.matchInfo.matchCard.specialEventsCards.playerSubstitulation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.soccer.domain.EventEntity

@Composable
fun LeftSubstitulation(event: EventEntity){
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
                        .padding(end = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(event.playerFullName, modifier = Modifier.padding(bottom = 4.dp))
                    Text(event.replacement.playerFullName)
                }

                ColumnOfPolygons()
            }
        }
    }
}