package com.example.sportapp.pages

import AppActivityViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.api.viewModels.MatchesState
import com.example.sportapp.api.viewModels.RankingsActivityViewModel
import com.example.sportapp.widgets.matches.MatchesContent
import com.example.sportapp.widgets.matches.MatchesPageNavigation
import com.example.sportapp.widgets.matches.rankigs.RankingsContent


//класс для упраления контентом на страница матчи-ранкинги
sealed class MatchesRankingsScreen(val route: String) {
    object MatchesPage : MatchesRankingsScreen("matchesScreen")
    object RankingsPage : MatchesRankingsScreen("rankings")
}


//контент страницы матчей
@Composable
fun MatchesPage(
    matchesViewModel: MatchesActivityViewModel,
    state: MatchesState,
    appActivity: AppActivityViewModel,
    rankingsViewModel: RankingsActivityViewModel = viewModel(),
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
                //экран с матчами
                MatchesContent(state, rankingsState, matchesViewModel, appActivity)
            }

            composable(MatchesRankingsScreen.RankingsPage.route) {
                //экран с ранкингами
                RankingsContent(rankingsState, appActivity)
            }

        }
    }
}

