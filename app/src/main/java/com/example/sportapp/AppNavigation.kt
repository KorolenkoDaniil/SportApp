package com.example.sportapp

import AppActivityViewModel
import BottomNavBar
import TopAppBar
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
import com.example.sportapp.models.user.AuthViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.NewsSate
import com.example.sportapp.models.viewModels.VideosState
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.pages.FirstPage
import com.example.sportapp.pages.HomePage
import com.example.sportapp.pages.LikePage
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
) {

    val showBars by appActivity.showBars.collectAsState()

    Scaffold(
        containerColor = Color(0xFFF6F6F6),

        bottomBar = {
            if (showBars) {
                BottomNavBar(navController = navController)
            }
        },

        topBar = {
            if (showBars) {
                TopAppBar(appActivity, authViewModel, navController)
            }
        }
    ) { innerPadding ->

        val paddings = if (showBars) 8.dp else 0.dp



        NavHost(
            navController = navController,
            startDestination = Screen.FirstPage.route,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = paddings),
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
//                        authViewModel = authViewModel,
                    )
                }

                composable(Screen.FirstPage.route) {
                    FirstPage(navController)
                }
                composable(Screen.Matches.route) {

                    MatchesPage(matchesViewModel, state, appActivity)
                }
                composable(Screen.Video.route) { VideoPage(appActivity) }

                composable(Screen.Like.route) { LikePage(appActivity) }

                composable(Screen.News.route) { backStackEntry ->
                    val newsDateTime = backStackEntry.arguments?.getString("newsId")
                    NewsPage(
                        appActivity,
                        newsDateTime!!,
                        newsState,
                        navController,
                        newsViewModel
                    )
                }
            }
        )
    }
}