package com.example.sportapp.pagesAndWidgets.pages

import AppActivityViewModel
import BottomNavBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sportapp.models.viewModels.AIAnswerViewModel
import com.example.sportapp.models.viewModels.AnswerState
import com.example.sportapp.models.viewModels.AuthState
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.NewsState
import com.example.sportapp.models.viewModels.VideosState
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.pagesAndWidgets.widgets.shared.Loading
import java.time.LocalDateTime


sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Matches : Screen("matches")
    data object VideoListPage : Screen("video")
    data object Like : Screen("like")
    data object News : Screen("news/{newsId}")
    data object FirstPage : Screen("firstPage")
    data object LoginPage : Screen("login")
    data object SignupPage : Screen("signup")
    data object Loading : Screen("loading")
    data object VideoPlayerPage : Screen("videoPlayer")
}


@Composable
fun MyAppNavigation(
    authViewModel: AuthViewModel,
    newsState: NewsState,
    state: MatchesState,
    videoState: VideosState,
    newsViewModel: NewsActivityViewModel,
    appActivity: AppActivityViewModel,
    navController: NavHostController,
    matchesViewModel: MatchesActivitySoccerViewModel,
    videoViewModel: YoutubeActivityViewModel,
    authState: AuthState,
    AIViewModel: AIAnswerViewModel,
    answerState: AnswerState,
    startDestination: String,
) {

    val showBar = remember { mutableStateOf(false)}

    Scaffold(
        containerColor = Color(0xFFEBEFF4),

        bottomBar = {
            if (showBar.value) {
                BottomNavBar(navController = navController, appActivity)
            }
        },

        ) { innerPadding ->


        val topPaddings = 14.dp
        val horizontalPaddings = 12.dp


        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = topPaddings),
            builder = {
                composable(Screen.LoginPage.route) {
                    showBar.value = false
                    LoginPage(navController, authViewModel)
                }
                composable(Screen.SignupPage.route) {
                    showBar.value = false
                    SignupPage(navController, authViewModel)
                }
                composable(Screen.Loading.route) {
                    Loading()
                }
                composable(Screen.Home.route) {
                    showBar.value = true
                    HomePage(
                        newsState = newsState,
                        state = state,
                        videoState = videoState,
                        newsViewModel = newsViewModel,
                        matchesViewModel = matchesViewModel,
                        videoViewModel = videoViewModel,
                        navController = navController,
                        authViewModel.currentUser,
                        authViewModel,
                        horizontalPaddings
                    )
                }

                composable(Screen.FirstPage.route) {
                    FirstPage(navController, authState)
                }
                composable(Screen.Matches.route) {

                    MatchesPage(
                        matchesViewModel, state, appActivity, navController, horizontalPaddings)
                }
                composable(Screen.VideoListPage.route) {
                    VideoListPage(
                        videoViewModel = videoViewModel,
                        navController = navController
                    )
                }

                composable(Screen.Like.route) {
                    AIChatPage(
                        AIViewModel, authViewModel
                    )
                }

                composable(Screen.News.route) { backStackEntry ->

                    val newsDateTime = backStackEntry.arguments?.getString("newsId")

                    val parsedDateTime = LocalDateTime.parse(newsDateTime!!)

                    NewsPage(
                        appActivity,
                        parsedDateTime.toString(),
                        newsState,
                        navController,
                        newsViewModel,
                        horizontalPaddings,
                        authViewModel,
                        showBar
                    )
                }

                composable (Screen.VideoPlayerPage.route) {
                    VideoPlayerPage(videoViewModel)
                }
            }
        )
    }
}