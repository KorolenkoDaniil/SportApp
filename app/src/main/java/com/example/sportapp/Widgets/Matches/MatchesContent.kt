package com.example.sportapp.widgets.matches

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            CommonError(mainViewModel, "Error loading matches")
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
                                val matchId = backStackEntry.arguments?.getString("matchId")
                                val matchDayNumber = backStackEntry.arguments?.getString("matchDayNumber")?.toIntOrNull()
                                MatchInfo(matchId, state.matchDays, matchDayNumber)
                            }

                        }
                    }
                }


                RankingsState.Load -> {
                    Loading()
                }


                is RankingsState.Error -> {
                    CommonError(mainViewModel, "Error loading rankings")
                }
            }
        }
    }
}




