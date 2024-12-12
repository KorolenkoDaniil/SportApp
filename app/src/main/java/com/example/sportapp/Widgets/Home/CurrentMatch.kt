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
import com.example.sportapp.Widgets.Home.current_match_features.MiddleLine
import com.example.sportapp.Widgets.Home.current_match_features.Top_line
import com.example.sportapp.ui.theme.Blue100

@Composable
fun CurrentMatch() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Blue100)
            .height(176.dp)
    ) {
        Column {
            Top_line()
            MiddleLine()
        }
    }
}

