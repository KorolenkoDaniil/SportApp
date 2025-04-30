package com.example.sportapp.presentation.navigation

import BottomNavBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.containers.StatesContainer
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.presentation.widgets.common.shared.Loading
import com.example.sportapp.presentation.widgets.screens.aIChat.AIChatPage
import com.example.sportapp.presentation.widgets.screens.home.HomePage
import com.example.sportapp.presentation.widgets.screens.home.NewsPage
import com.example.sportapp.presentation.widgets.screens.matches.MatchesPage
import com.example.sportapp.presentation.widgets.screens.signUpIn.FirstPage
import com.example.sportapp.presentation.widgets.screens.signUpIn.LoginPage
import com.example.sportapp.presentation.widgets.screens.signUpIn.SignupPage
import com.example.sportapp.presentation.widgets.screens.videos.VideoListPage
import com.example.sportapp.presentation.widgets.screens.videos.VideoPlayerPage
import java.time.LocalDateTime

@Composable
fun MyAppNavigation(
    viewModels: ViewModelContainer,
    states: StatesContainer,
    navController: NavHostController,

    startDestination: String,
) {

    val showBar = remember { mutableStateOf(false)}

    Scaffold(
        containerColor = Color(0xFFEBEFF4),

        bottomBar = {
            if (showBar.value) {
                BottomNavBar(navController = navController, viewModels.appActivity)
            }
        },

        ) { innerPadding ->


        val topPaddings = 14.dp
        val horizontalPaddings = 12.dp

        val itemList = remember { mutableStateListOf<NewsEntity>() }


        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = topPaddings),
            builder = {
                composable(Screen.LoginPage.route) {
                    showBar.value = false
                    LoginPage(navController, viewModels.authViewModel)
                }
                composable(Screen.SignupPage.route) {
                    showBar.value = false
                    SignupPage(navController, viewModels.authViewModel)
                }
                composable(Screen.Loading.route) {
                    Loading()
                }
                composable(Screen.Home.route) {
                    showBar.value = true
                    HomePage(
                        viewModels = viewModels,
                        states = states,
                        navController = navController,
                        user = viewModels.authViewModel.currentUser,
                        horizontalPaddings = horizontalPaddings,
                        itemList
                    )
                }

                composable(Screen.FirstPage.route) {
                    FirstPage(navController, states.authState)
                }
                composable(Screen.Matches.route) {

                    MatchesPage(
                        viewModels.matchesViewModel, states.matchesState, viewModels.appActivity, navController, horizontalPaddings)
                }
                composable(Screen.VideoListPage.route) {
                    VideoListPage(
                        videoViewModel = viewModels.videoViewModel,
                        navController = navController
                    )
                }

                composable(Screen.Like.route) {
                    AIChatPage(
                        viewModels.aiViewModel, viewModels.authViewModel
                    )
                }

                composable(Screen.News.route) { backStackEntry ->

                    val newsDateTime = backStackEntry.arguments?.getString("newsId")

                    val parsedDateTime = LocalDateTime.parse(newsDateTime!!)

                    NewsPage(
                        viewModels,
                        states,
                        parsedDateTime.toString(),
                        navController,
                        horizontalPaddings,
                        showBar,
                        itemList
                    )
                }

                composable (Screen.VideoPlayerPage.route) {
                    VideoPlayerPage(viewModels.videoViewModel)
                }
            }
        )
    }
}