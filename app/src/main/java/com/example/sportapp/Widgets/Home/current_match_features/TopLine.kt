package com.example.sportapp.Widgets.Home.current_match_features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sportapp.Shared.LiveTranslationFlag
import com.example.sportapp.ui.theme.style3

@Composable
fun Top_line() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        LiveTranslationFlag(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .zIndex(1f)
        )
        Text(
            text = "League name",
            style = style3,
            modifier = Modifier
                .align(Alignment.Center)
                .zIndex(0f)
        )
    }
}