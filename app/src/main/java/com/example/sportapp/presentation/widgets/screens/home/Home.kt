package com.example.sportapp.presentation.widgets.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.containers.StatesContainer
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.NewsState
import com.example.sportapp.models.viewModels.VideosState
import com.example.sportapp.presentation.navigation.Screen
import com.example.sportapp.presentation.widgets.common.shared.CommonError
import com.example.sportapp.presentation.widgets.common.shared.Loading
import com.example.sportapp.presentation.widgets.screens.home.home.HomePageContent
import kotlinx.coroutines.flow.StateFlow


@Composable
fun HomePage(
    viewModels: ViewModelContainer,
    states: StatesContainer,
    navController: NavHostController,
    user: StateFlow<UserEntity?>,
    horizontalPaddings: Dp
) {
    when ( states.matchesState) {

        is MatchesState.MatchesContent -> {

            when (states.videoState) {

                is VideosState.VideosContent -> {

                    when (states.newsState) {

                        is NewsState.NewsContent -> {

                            HomePageContent(
                                user = user,
                                viewModels,
                                navController = navController,
                                news = states.newsState.news,
                                horizontalPaddings
                            )
                        }

                        is NewsState.Error -> {
                            Log.d("tttNews", "ошибка Home")
                            CommonError(viewModels.newsViewModel, Screen.Home.route, navController, "статитистика")
                        }

                        is NewsState.Load -> {
                            Loading()
                        }
                    }
                }

                is VideosState.Error ->
                    CommonError(viewModels.videoViewModel, Screen.Home.route, navController, "видео")


                is VideosState.Load -> Loading()
            }
        }

        is MatchesState.Error -> {
            CommonError(viewModels.matchesViewModel, Screen.Home.route, navController, "матчи")
        }

        MatchesState.Load -> {
            Loading()
        }
    }
}

