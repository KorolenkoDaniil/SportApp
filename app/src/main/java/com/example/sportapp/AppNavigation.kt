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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebaseexample.pages.LoginPage
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
import com.example.sportapp.pages.MatchesPage
import com.example.sportapp.pages.NewsPage
import com.example.sportapp.pages.VideoPage


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Matches : Screen("matches")
    object Video : Screen("video")
    object Like : Screen("like")
    object News : Screen("news/{newsId}")
    object FirstPage : Screen("firstPage")
    object LoginPage : Screen("login")
    object SignupPage : Screen("signup")
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
                TopAppBar(appActivity)
            }
        }
    ) { innerPadding ->

        val paddings: Dp

        if (showBars) paddings = 8.dp else paddings = 0.dp


        NavHost(
            navController = navController,
            startDestination = Screen.LoginPage.route,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = paddings),
            builder = {
                composable(Screen.LoginPage.route) {
                    LoginPage(navController, authViewModel)
                }
                composable(Screen.SignupPage.route) {
                    SignupPage(navController, authViewModel)
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
                        authViewModel = authViewModel,
                    )
                }

                composable(Screen.FirstPage.route) {
                    FirstPage(
                        newsState,
                        state,
                        videoState,
                        newsViewModel,
                        matchesViewModel,
                        videoViewModel,
                        navController
                    )
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