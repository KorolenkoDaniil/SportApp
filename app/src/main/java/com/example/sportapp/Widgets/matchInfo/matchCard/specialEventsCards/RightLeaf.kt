package com.example.sportapp.widgets.matchInfo.matchCard.specialEventsCards

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sportapp.domain.EventEntity

@Composable
fun RightLeaf (event: EventEntity, iconId: Int, iconSize: Dp){
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
                Image(
                    painter = painterResource(iconId),
                    contentDescription = "greenPolygon",
                    modifier = Modifier
                        .size(iconSize)
                        .align(Alignment.CenterVertically),
                )

                Column(
                    Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(event.playerFullName, modifier = Modifier.padding(bottom = 4.dp).fillMaxWidth(), style = TextStyle(textAlign = TextAlign.End))

                }
            }
        }
    }
}