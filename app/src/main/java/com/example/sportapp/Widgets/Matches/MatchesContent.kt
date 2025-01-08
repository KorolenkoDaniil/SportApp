package com.example.sportapp.widgets.matches

import AppActivityViewModel
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.api.viewModels.MatchActivityViewModel
import com.example.sportapp.api.viewModels.MatchReportActivityViewModel
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.api.viewModels.MatchesState
import com.example.sportapp.api.viewModels.RankingsState
import com.example.sportapp.pages.MatchInfo
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.widgets.matches.calendar.CalendarTab
import com.example.sportapp.widgets.matches.matchesList.MatchesList
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
    matchesViewModel: MatchesActivityViewModel,
    
    appActivity: AppActivityViewModel,
    ) {

    //контроллер страниц матчи-информация про матчи
    val matchesMatchInfoNavController = rememberNavController()


    when (state) {

        //ошибька загрузки матчей
        is MatchesState.Error -> {
            CommonError(matchesViewModel)
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
                                        matchesMatchInfoNavController
                                    )

                                }
                            }


                            //путь и контент инфформации про 1 матч
                            composable(
                                route = MatchesMatchInfoScreen.MatchInfoPage.route
                            ) { backStackEntry ->
                                Log.d("tttDebug", "NavHost: маршрут изменён")
                                val matchId = backStackEntry.arguments?.getString("matchId")
                                val matchDayNumber =
                                    backStackEntry.arguments?.getString("matchDayNumber")
                                        ?.toIntOrNull()
                                Log.d(
                                    "tttDebug",
                                    "MatchInfo вызван с matchId: $matchId, matchDayNumber: $matchDayNumber"
                                )


                                val matchReportViewModel: MatchReportActivityViewModel = viewModel()
                                val matchViewModel: MatchActivityViewModel = viewModel()


                                if (matchId != null) {

                                    matchReportViewModel.loadMatchReport(matchId)
                                    matchViewModel.loadData()

                                    MatchInfo(
                                        matchReportViewModel,
                                        matchViewModel,
                                        matchesViewModel,
                                        appActivity
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
                    CommonError(matchesViewModel)
                }

            }
        }
    }
}




