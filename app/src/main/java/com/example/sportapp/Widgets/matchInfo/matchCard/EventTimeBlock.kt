package com.example.sportapp.widgets.matchInfo.matchCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
fun EventTimeBlock (event: EventEntity){
    Row(
        modifier = Modifier.size(height = 40.dp, width = 50.dp).background(color = Color(0xFF0f089e), shape = RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = event.minute.toString() + "'", style = style7)
    }
}