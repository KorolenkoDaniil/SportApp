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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.soccer.api.domain.EventEntity

@Composable
fun RightSubstitulation(event: EventEntity) {
    Row(Modifier.padding(start = 12.dp)) {
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
                ColumnOfPolygons()

                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        event.playerFullName,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(
                            textAlign = TextAlign.End
                        )
                    )
                    Text(
                        event.replacement.playerFullName,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(
                            textAlign = TextAlign.End
                        )
                    )
                }
            }
        }
    }
}