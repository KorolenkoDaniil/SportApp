package com.example.sportapp.presentation.widgets.screens.matches

import AppActivityViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.RankingsActivitySoccerViewModel
import com.example.sportapp.presentation.widgets.screens.matches.matches.MatchesContent
import com.example.sportapp.presentation.widgets.screens.matches.matches.MatchesPageNavigation
import com.example.sportapp.presentation.widgets.screens.matches.matches.rankigs.RankingsContent


sealed class MatchesRankingsScreen(val route: String) {
    object MatchesPage : MatchesRankingsScreen("matchesScreen")
    object RankingsPage : MatchesRankingsScreen("rankings")
}


//контент страницы матчей
@Composable
fun MatchesPage(
    matchesViewModel: MatchesActivitySoccerViewModel,
    state: MatchesState,
    appActivity: AppActivityViewModel,
    navController: NavHostController,
    horizontalPaddings: Dp,
    rankingsViewModel: RankingsActivitySoccerViewModel = viewModel(),
    ) {

    //получение состоний вьюмоделей

    val rankingsState by rankingsViewModel.getState().collectAsState()

    //контроллер навигации страницы матчи-ранкинги
    val matchesRankingsNavController = rememberNavController()


    Column {

        //кнопки для навигации межджду контентом, потом переделать
        MatchesPageNavigation(matchesRankingsNavController)

        //навигация между контентом страинцы матчи-ранкинги
        NavHost(
            navController = matchesRankingsNavController,
            startDestination = MatchesRankingsScreen.MatchesPage.route
        ) {

            composable(MatchesRankingsScreen.MatchesPage.route) {
                MatchesContent(state, rankingsState, matchesViewModel, appActivity, navController, horizontalPaddings)
            }

            composable(MatchesRankingsScreen.RankingsPage.route) {
                //экран с ранкингами
                RankingsContent(rankingsState, appActivity)
            }

        }
    }
}

