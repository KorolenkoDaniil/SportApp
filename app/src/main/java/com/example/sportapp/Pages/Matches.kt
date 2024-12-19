package com.example.sportapp.pages

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

sealed class MatchesRankingsScreen(val route: String) {
    object MatchesPage : MatchesRankingsScreen("matchesScreen")
    object RankingsPage : MatchesRankingsScreen("rankings")
}

@Composable
fun MatchesPage(
    mainViewModel: MatchesActivityViewModel = viewModel(),
    rankingsViewModel: RankingsActivityViewModel = viewModel()
) {
    val state by mainViewModel.getState().collectAsState()
    val rankingsState by rankingsViewModel.getState().collectAsState()

    val matchesRankingsNavController = rememberNavController()

    Column {

        MatchesPageNavigation(matchesRankingsNavController)

        NavHost(
            navController = matchesRankingsNavController,
            startDestination = MatchesRankingsScreen.MatchesPage.route
        ) {
            composable(MatchesRankingsScreen.MatchesPage.route) {
                MatchesContent(state, rankingsState, mainViewModel)
            }
            composable(MatchesRankingsScreen.RankingsPage.route) {
                RankingsContent(rankingsState)
            }
        }
    }
}

