package com.example.sportapp.widgets.matches

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportapp.pages.MatchScreen


@Composable
fun MatchesPageNavigation (matchesNavController: androidx.navigation.NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Button(onClick = { matchesNavController.navigate(MatchScreen.MatchesPage.route) }) {
            Text("Matches")
        }
        Button(onClick = { matchesNavController.navigate(MatchScreen.RankingsPage.route) }) {
            Text("Rankings")
        }
    }
}