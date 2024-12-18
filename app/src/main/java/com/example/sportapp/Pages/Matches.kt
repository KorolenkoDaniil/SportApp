package com.example.sportapp.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.api.viewModels.RankingsActivityViewModel
import com.example.sportapp.widgets.matches.MatchesContent
import com.example.sportapp.widgets.matches.MatchesPageNavigation
import com.example.sportapp.widgets.matches.rankigs.RankingsContent

sealed class MatchScreen(val route: String) {
    object MatchesPage : MatchScreen("matchesScreen")
    object RankingsPage : MatchScreen("rankings")
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MatchesPage(
    mainViewModel: MatchesActivityViewModel = viewModel(),
    rankingsViewModel: RankingsActivityViewModel = viewModel()
) {
    val state by mainViewModel.state.collectAsState()
    val rankingsState by rankingsViewModel.state.collectAsState()

    val matchesNavController = rememberNavController()

    Column {

        MatchesPageNavigation(matchesNavController)

        NavHost(
            navController = matchesNavController,
            startDestination = MatchScreen.MatchesPage.route
        ) {
            composable(MatchScreen.MatchesPage.route) {
                MatchesContent(state, rankingsState, mainViewModel)
            }
            composable(MatchScreen.RankingsPage.route) {
                RankingsContent(rankingsState)
            }
        }
    }
}

