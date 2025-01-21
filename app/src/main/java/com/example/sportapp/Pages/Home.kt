package com.example.sportapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.models.user.AuthState
import com.example.sportapp.models.user.AuthViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.NewsSate
import com.example.sportapp.models.viewModels.VideosState
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.ui.theme.style1
import com.example.sportapp.widgets.home.CurrentMatch
import com.example.sportapp.widgets.home.NewsCardRow
import com.example.sportapp.widgets.home.VideoCardRow


@Composable
fun HomePage(
    newsState: NewsSate,
    state: MatchesState,
    videoState: VideosState,
    newsViewModel: NewsActivityViewModel,
    matchesViewModel: MatchesActivitySoccerViewModel,
    videoViewModel: YoutubeActivityViewModel,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {


    val authState = authViewModel.authState.observeAsState()

//    LaunchedEffect (authState.value) {
//
//

    when (authState.value) {
        is AuthState.Unauthenticated -> navController.navigate("login")
        else -> Unit
    }
//    }


    when (state) {

        is MatchesState.MatchesContent -> {

            when (videoState) {

                is VideosState.VideosContent -> {

                    when (newsState) {

                        is NewsSate.NewsContent -> {


                            LazyColumn {
                                item { CurrentMatch(matchesViewModel.nearestMatch) }
                                item { Spacer(modifier = Modifier.height(32.dp)) }
                                item { Text(text = "Sport news", style = style1) }
                                item { Spacer(modifier = Modifier.height(24.dp)) }
                                item { NewsCardRow(navController, newsViewModel, newsState.news) }
                                item { Spacer(modifier = Modifier.height(24.dp)) }
                                item { Text(text = "Highlights", style = style1) }
                                item { Spacer(modifier = Modifier.height(24.dp)) }
                                item { VideoCardRow(videoState.videos.items) }
                                item { Spacer(modifier = Modifier.height(24.dp)) }
                            }
                        }

                        is NewsSate.Error -> {
                            Log.d("tttNews", "ошибка Home")
                            CommonError(newsViewModel)
                        }

                        is NewsSate.Load -> Loading()
                    }
                }

                is VideosState.Error ->
                    CommonError(videoViewModel)


                is VideosState.Load -> Loading()
            }
        }

        //ошибька загрузки матчей
        is MatchesState.Error -> {
            CommonError(matchesViewModel)
        }

        //загрузка матчей
        MatchesState.Load -> {
            Loading()
        }
    }
}


//
//
//NavHost(
//navController = matchesMatchInfoNavController
//
//startDestination = MatchesMatchInfoScreen.MatchesPage.route
//) {
//
//    //путь и контент списка матчей
//    composable(MatchesMatchInfoScreen.MatchesPage.route) {
//        Log.d("tttDebug", "NavHost: маршрут изменён")
//
//        appActivity.changePageName("Match schedule")
//        //контент страницы страницы матчей
//        Column {
//
//            //календарь
//            CalendarTab(pageState, state)
//            Spacer(Modifier.height(16.dp))
//
//            //список матчей
//            MatchesList(
//                pageState = pageState,
//                matchDays = state.matchDays,
//                rankings = rankingsState.rankings,
//                matchesMatchInfoNavController
//            )
//
//        }
//    }
//
//
//    composable(
//        route = MatchesMatchInfoScreen.MatchInfoPage.route
//    ) { backStackEntry ->
//        Log.d("tttDebug", "NavHost: маршрут изменён")
//        val matchId = backStackEntry.arguments?.getString("matchId")
//
//
//        val matchReportViewModel: MatchReportActivitySoccerViewModel = viewModel()
//        val matchViewModel: MatchActivitySoccerViewModel = viewModel()
//
//        if (matchId != null) {
//
//            matchReportViewModel.loadMatchReport(matchId)
//            matchViewModel.loadMatchData(matchId)
//
//            MatchInfo(
//                matchReportViewModel,
//                matchViewModel,
//                appActivity
//            )
//        }
//    }
//
//}
//}
//}
//
//
////загрузка ранкингов
//RankingsState.Load -> {
//    Loading()
//}
//
//
////ошибка загрузки ранкингов
//is RankingsState.Error -> {
//    CommonError(matchesViewModel)
//}

