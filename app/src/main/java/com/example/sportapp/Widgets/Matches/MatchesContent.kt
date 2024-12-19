@file:Suppress("NAME_SHADOWING")

package com.example.sportapp.widgets.matches

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.api.viewModels.MatchReportActivityViewModel
import com.example.sportapp.api.viewModels.MatchState
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.api.viewModels.RankingsState
import com.example.sportapp.pages.MatchInfo
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.widgets.matches.calendar.CalendarTab
import com.example.sportapp.widgets.matches.matchesList.MatchesList


sealed class MatchesMatchInfoScreen(val route: String) {
    object MatchesPage : MatchesMatchInfoScreen("matchesScreen")
    object MatchInfoPage : MatchesMatchInfoScreen("matchInfo/{matchId}/{matchDayNumber}")
}

@SuppressLint("NewApi")
@Composable
fun MatchesContent(
    state: MatchState,
    rankingsState: RankingsState,
    mainViewModel: MatchesActivityViewModel
) {

    val matchesMatchInfoNavController = rememberNavController()


    when (state) {
        is MatchState.Error -> {
            CommonError(mainViewModel)
        }

        MatchState.Load -> {
            Loading()
        }

        is MatchState.MatchContent -> {
            when (rankingsState) {
                is RankingsState.RankingsContent -> {
                    val pageState = rememberPagerState(pageCount = { state.matchDays.size })

                    Column {

                        NavHost(
                            navController = matchesMatchInfoNavController,
                            startDestination = MatchesMatchInfoScreen.MatchesPage.route
                        ) {
                            composable(MatchesMatchInfoScreen.MatchesPage.route) {
                                Log.d("tttDebug", "NavHost: маршрут изменён")

                                Column {
                                    CalendarTab(pageState, state)
                                    Spacer(Modifier.height(16.dp))
                                    MatchesList(
                                        pageState = pageState,
                                        matchDays = state.matchDays,
                                        rankings = rankingsState.rankings,
                                        matchesMatchInfoNavController
                                    )
                                }
                            }
                            composable(
                                route = MatchesMatchInfoScreen.MatchInfoPage.route
                            ) { backStackEntry ->
                                Log.d("tttDebug", "NavHost: маршрут изменён")
                                val matchId = backStackEntry.arguments?.getString("matchId")
                                val matchDayNumber = backStackEntry.arguments?.getString("matchDayNumber")?.toIntOrNull()
                                Log.d("tttDebug", "MatchInfo вызван с matchId: $matchId, matchDayNumber: $matchDayNumber")
                                val matchReportViewModel: MatchReportActivityViewModel = viewModel()
                                if (matchId != null) {
                                    val matchReportViewModel: MatchReportActivityViewModel = viewModel()
                                    matchReportViewModel.loadMatchReport(matchId)
                                    MatchInfo(matchId, state.matchDays, matchDayNumber, matchReportViewModel)
                                }
                                MatchInfo(matchId, state.matchDays, matchDayNumber, matchReportViewModel)
                            }

                        }
                    }
                }


                RankingsState.Load -> {
                    Loading()
                }


                is RankingsState.Error -> {
                    CommonError(mainViewModel)
                }
            }
        }
    }
}




