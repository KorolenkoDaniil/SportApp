package com.example.sportapp.widgets.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.soccer.api.domain.MatchEntity
import com.example.sportapp.ui.theme.Blue100
import com.example.sportapp.widgets.home.currentMatchFeatures.MiddleLine
import com.example.sportapp.widgets.home.currentMatchFeatures.Top_line

@Composable
fun CurrentMatch(
    match: MatchEntity
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Blue100)
            .height(176.dp)
    ) {
        Column {
            Top_line(match)
            MiddleLine(match)
        }
    }
}


