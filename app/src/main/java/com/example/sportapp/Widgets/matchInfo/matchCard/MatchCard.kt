package com.example.sportapp.widgets.matchInfo.matchCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.domain.EventEntity
import com.example.sportapp.ui.theme.style7

@Composable
fun MatchCard(event: EventEntity){
    Row(
        Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f)
        )
        Column {

            Row(
                modifier = Modifier.size(height = 30.dp, width = 40.dp).background(color = Color(0xFF0f089e), shape = RoundedCornerShape(8.dp)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = event.minute.toString() + "'", style = style7)
            }

            LineImage()
        }
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f)
        )
    }

}