package com.example.sportapp.presentation.widgets.screens.matches.matches.matchesList.matchesCard.cardParts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportapp.CleanArchitexture.domain.models.match.MatchEntity

@Composable
fun ScorePart(modifier: Modifier, item: MatchEntity) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "${item.goalsTeamA} : ${item.goalsTeamB}")
    }
}

