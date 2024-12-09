package com.example.sportapp.Shared.BottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun UserImage() {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(50))
            .background(color = Color.Gray)
    ) {}
}
