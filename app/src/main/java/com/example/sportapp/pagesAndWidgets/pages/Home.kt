package com.example.sportapp.pagesAndWidgets.pages

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.example.sportapp.models.user.domain.UserEntity
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.NewsState
import com.example.sportapp.models.viewModels.VideosState
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.pagesAndWidgets.widgets.home.HomePageContent
import com.example.sportapp.pagesAndWidgets.widgets.shared.CommonError
import com.example.sportapp.pagesAndWidgets.widgets.shared.Loading
import kotlinx.coroutines.flow.StateFlow


@Composable
fun HomePage(
    newsState: NewsState,
    state: MatchesState,
    videoState: VideosState,
    newsViewModel: NewsActivityViewModel,
    matchesViewModel: MatchesActivitySoccerViewModel,
    videoViewModel: YoutubeActivityViewModel,
    navController: NavHostController,
    user: StateFlow<UserEntity?>,
    authViewModel: AuthViewModel,
    horizontalPaddings: Dp
) {
    when (state) {

        is MatchesState.MatchesContent -> {

            when (videoState) {

                is VideosState.VideosContent -> {

                    when (newsState) {

                        is NewsState.NewsContent -> {
                            HomePageContent(
                                user = user,
                                authViewModel = authViewModel,
                                newsViewModel = newsViewModel,
                                matchesViewModel = matchesViewModel,
                                navController = navController,
                                news = newsState.news,
                                videoViewModel = videoViewModel,
                                horizontalPaddings
                            )
                        }

                        is NewsState.Error -> {
                            Log.d("tttNews", "ошибка Home")
                            CommonError(newsViewModel, Screen.Home.route, navController)
                        }

                        is NewsState.Load -> {
                            Loading()
                        }
                    }
                }

                is VideosState.Error ->
                    CommonError(videoViewModel, Screen.Home.route, navController)


                is VideosState.Load -> Loading()
            }
        }

        is MatchesState.Error -> {
            CommonError(matchesViewModel, Screen.Home.route, navController)
        }

        MatchesState.Load -> {
            Loading()
        }
    }
}

