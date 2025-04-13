package com.example.sportapp.presentation.widgets.screens.home.home.currentMatchFeatures

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sportapp.CleanArchitexture.domain.models.match.MatchEntity
import com.example.sportapp.presentation.widgets.common.shared.LiveTranslationFlag
import com.example.sportapp.ui.theme.style3

@Composable
fun TopLine(nearestMatch: MatchEntity) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        if (nearestMatch.matchStatus == 1) {
            LiveTranslationFlag()
        }
        Text(
            text = "League name",
            style = style3,
            modifier = Modifier
                .align(Alignment.Center)
                .zIndex(0f)
        )
    }
}