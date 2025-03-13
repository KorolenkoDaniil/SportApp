package com.example.sportapp

import AppActivityViewModel
import BottomNavBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebaseexample.pages.SignupPage
import com.example.sportapp.models.viewModels.AIAnswerViewModel
import com.example.sportapp.models.viewModels.AnswerState
import com.example.sportapp.models.viewModels.AuthState
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.NewsSate
import com.example.sportapp.models.viewModels.VideosState
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.pages.AIChatPage
import com.example.sportapp.pages.FirstPage
import com.example.sportapp.pages.HomePage
import com.example.sportapp.pages.LoginPage
import com.example.sportapp.pages.MatchesPage
import com.example.sportapp.pages.NewsPage
import com.example.sportapp.pages.VideoPage
import com.example.sportapp.shared.Loading


sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Matches : Screen("matches")
    data object Video : Screen("video")
    data object Like : Screen("like")
    data object News : Screen("news/{newsId}")
    data object FirstPage : Screen("firstPage")
    data object LoginPage : Screen("login")
    data object SignupPage : Screen("signup")
    data object Loading : Screen("loading")
}


@Composable
fun MyAppNavigation(
    authViewModel: AuthViewModel,
    newsState: NewsSate,
    state: MatchesState,
    videoState: VideosState,
    newsViewModel: NewsActivityViewModel,
    appActivity: AppActivityViewModel,
    navController: NavHostController,
    matchesViewModel: MatchesActivitySoccerViewModel,
    videoViewModel: YoutubeActivityViewModel,
    authState: AuthState,
    AIViewModel: AIAnswerViewModel,
    answerState: AnswerState
) {

    val showBars by appActivity.showBars.collectAsState()
    val AIChatPagePaddings by appActivity.AIChatPagePaddings.collectAsState()

    Scaffold(
        containerColor = Color(0xFFEBEFF4),

        bottomBar = {
            if (showBars) {
                BottomNavBar(navController = navController, appActivity)
            }
        },

        ) { innerPadding ->

//        val topPaddings = if (showBars) 20.dp else 0.dp
//        val horizontalPaddings = if (AIChatPagePaddings) 0.dp else 16.dp

        val topPaddings = 20.dp
        val horizontalPaddings = 16.dp


        NavHost(
            navController = navController,
            startDestination = Screen.FirstPage.route,
            modifier = Modifier
                .padding(innerPadding)
//                .padding(horizontal = horizontalPaddings)
//                .padding(top = topPaddings),
            ,
            builder = {
                composable(Screen.LoginPage.route) {
                    appActivity.changeShowBars(false)
                    LoginPage(navController, authViewModel)
                }
                composable(Screen.SignupPage.route) {
                    appActivity.changeShowBars(false)
                    SignupPage(navController, authViewModel)
                }
                composable(Screen.Loading.route) {
                    Loading()
                }
                composable(Screen.Home.route) {
                    appActivity.changeShowBars(true)
                    HomePage(
                        newsState = newsState,
                        state = state,
                        videoState = videoState,
                        newsViewModel = newsViewModel,
                        matchesViewModel = matchesViewModel,
                        videoViewModel = videoViewModel,
                        navController = navController,
                        authViewModel.currentUser,
                        topPaddings,
                        horizontalPaddings,
                    )
                }

                composable(Screen.FirstPage.route) {
                    FirstPage(navController, authState)
                }
                composable(Screen.Matches.route) {

                    MatchesPage(
                        matchesViewModel, state, appActivity, navController, topPaddings,
                        horizontalPaddings,
                    )
                }
                composable(Screen.Video.route) {
                    VideoPage(
                        appActivity, topPaddings,
                        horizontalPaddings,
                    )
                }

                composable(Screen.Like.route) {
                    AIChatPage(
                        AIViewModel, topPaddings,
                        horizontalPaddings,
                    )
                }

                composable(Screen.News.route) { backStackEntry ->
                    val newsDateTime = backStackEntry.arguments?.getString("newsId")
                    NewsPage(
                        appActivity,
                        newsDateTime!!,
                        newsState,
                        navController,
                        newsViewModel,
                        topPaddings,
                        horizontalPaddings,
                    )
                }
            }
        )
    }
}