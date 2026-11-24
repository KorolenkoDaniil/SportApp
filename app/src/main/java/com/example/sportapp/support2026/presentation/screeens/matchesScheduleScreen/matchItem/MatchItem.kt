package com.example.sportapp.support2026.presentation.screeens.matchesScheduleScreen.matchItem

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import com.example.sportapp.support2026.features.soccer.domain.entities.Match

@Composable
fun MatchItem(
    match: Match,
    navController: NavHostController,
    logo1Painter: AsyncImagePainter,
    logo2Painter: AsyncImagePainter
) {

    val cardModifier = Modifier
        .fillMaxWidth()
        .height(144.dp)


    ElevatedCard(
        onClick = {
            val matchId = match.matchID
            Log.d("ttt", "matchId $matchId")

            navController.navigate("MatchDetails/${matchId}")
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = cardModifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFAFA)
        ),
    ) {
        Column() {
            MatchItemContent(
                team1Name = match.team1?.shortName.orEmpty(),
                team2Name = match.team2?.shortName.orEmpty(),
                logo1Painter = logo1Painter,
                logo2Painter = logo2Painter,
                match = match,
            )

            Spacer(Modifier.height(12.dp))
        }

    }
}

