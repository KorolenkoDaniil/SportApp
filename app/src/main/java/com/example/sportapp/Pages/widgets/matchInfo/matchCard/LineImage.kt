package com.example.sportapp.pages.widgets.matchInfo.matchCard


import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.size

@Composable
fun LineImage() {
    Image(
        painter = painterResource(id = R.drawable.line_2),
        contentDescription = "line",
        modifier = Modifier.size(height = 30.dp, width = 40.dp)
    )
}