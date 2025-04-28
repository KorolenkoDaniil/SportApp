package com.example.sportapp.presentation.widgets.screens.matches.matches

import AppActivityViewModel
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.models.viewModels.MatchActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchReportActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.RankingsState
import com.example.sportapp.presentation.navigation.Screen
import com.example.sportapp.presentation.widgets.common.shared.CommonError
import com.example.sportapp.presentation.widgets.common.shared.Loading
import com.example.sportapp.presentation.widgets.screens.matches.MatchInfo
import com.example.sportapp.presentation.widgets.screens.matches.matches.calendar.CalendarTab
import com.example.sportapp.presentation.widgets.screens.matches.matches.matchesList.MatchesList
import java.time.ZonedDateTime


//класс для управления частью контента (матчи матч дэй или ифнормация про 1 матч)
sealed class MatchesMatchInfoScreen(val route: String) {
    object MatchesPage : MatchesMatchInfoScreen("matchesScreen")
    object MatchInfoPage : MatchesMatchInfoScreen("matchInfo/{matchId}/{matchDayNumber}")
}


@Composable
fun MatchesContent(
    //состояниен загрузки матчей
    state: MatchesState,
    //состоняние загрузки ранкингов
    rankingsState: RankingsState,
    //модель матчей
    matchesViewModel: MatchesActivitySoccerViewModel,
    appActivity: AppActivityViewModel,
    navController: NavHostController,
    horizontalPaddings: Dp
    ) {

    //контроллер страниц матчи-информация про матчи
    val matchesMatchInfoNavController = rememberNavController()


    when (state) {

        //ошибька загрузки матчей
        is MatchesState.Error -> {
            CommonError(matchesViewModel,  Screen.Matches.route, navController, "матч контент")
        }

        //загрузка матчей
        MatchesState.Load -> {
            Loading()
        }

        //загрузка матчей успешна
        is MatchesState.MatchesContent -> {

            when (rankingsState) {

                //загрузка ранкингов успешна
                is RankingsState.RankingsContent -> {

                    val pageState = rememberPagerState(pageCount = { state.matchDays.size })

                    //переход к старинце с текущим матчем
                    LaunchedEffect(pageState) {

                        val currentTime = ZonedDateTime.now()
                        var i = -1

                        do {
                            i++
                            val matchDateTime =
                                ZonedDateTime.parse(state.matchDays[i].matches[0].matchStartTime)
                            Log.d(
                                "dateTime", "" +
                                        "matchDateTime  $matchDateTime" +
                                        "currentTime $currentTime" +
                                        "          $i"
                            )

                        } while (matchDateTime.isBefore(currentTime) || matchDateTime.isEqual(
                                currentTime
                            )
                        )

                        pageState.scrollToPage(i)
                    }

                    Column {
                        NavHost(
                            navController = matchesMatchInfoNavController,
                            startDestination = MatchesMatchInfoScreen.MatchesPage.route
                        ) {

                            //путь и контент списка матчей
                            composable(MatchesMatchInfoScreen.MatchesPage.route) {
                                Log.d("tttDebug", "NavHost: маршрут изменён")

                                appActivity.changePageName("Match schedule")
                                //контент страницы страницы матчей
                                Column {

                                    //календарь
                                    CalendarTab(pageState, state)
                                    Spacer(Modifier.height(16.dp))

                                    //список матчей
                                    MatchesList(
                                        pageState = pageState,
                                        matchDays = state.matchDays,
                                        rankings = rankingsState.rankings,
                                        matchesMatchInfoNavController,
                                        horizontalPaddings
                                    )

                                }
                            }


                            composable(
                                route = MatchesMatchInfoScreen.MatchInfoPage.route
                            ) { backStackEntry ->
                                Log.d("tttDebug", "NavHost: маршрут изменён")
                                val matchId = backStackEntry.arguments?.getString("matchId")


                                val matchReportViewModel: MatchReportActivitySoccerViewModel = viewModel()
                                val matchViewModel: MatchActivitySoccerViewModel = viewModel()

                                if (matchId != null) {

                                    matchReportViewModel.loadMatchReport(matchId)
                                    matchViewModel.loadMatchData(matchId)

                                    MatchInfo(
                                        matchReportViewModel,
                                        matchViewModel,
                                        appActivity,
                                        navController,
                                        horizontalPaddings
                                    )
                                }
                            }
                        }
                    }
                }


                //загрузка ранкингов
                RankingsState.Load -> {
                    Loading()
                }


                //ошибка загрузки ранкингов
                is RankingsState.Error -> {
                    CommonError(matchesViewModel, Screen.Matches.route, navController, "ранкинги")
                }

            }
        }
    }
}




