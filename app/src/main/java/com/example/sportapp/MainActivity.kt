package com.example.sportapp

import AppActivityViewModel
import BottomNavBar
import TopAppBar
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.NewsActivityViewModel
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
}


class mainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val appActivity: AppActivityViewModel = viewModel()

            val newsViewModel: NewsActivityViewModel = viewModel()
            val newsState by newsViewModel.getState().collectAsState()

            val matchesViewModel: MatchesActivitySoccerViewModel = viewModel()
            val state by matchesViewModel.getState().collectAsState()

            val videoViewModel: YoutubeActivityViewModel = viewModel()
            val videoState by videoViewModel.getState().collectAsState()

            val navController = rememberNavController()

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
                    startDestination = Screen.FirstPage.route,
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding( horizontal = paddings)
                ) {

                    composable(Screen.FirstPage.route) {
                        FirstPage(
                            newsState ,
                            state,
                            videoState,
                            newsViewModel,
                            matchesViewModel,
                            videoViewModel,
                            appActivity,
                            navController)
                    }

                    composable(Screen.Home.route) {
                        HomePage(
                            newsState ,
                            state,
                            videoState,
                            newsViewModel,
                            matchesViewModel,
                            videoViewModel,
                            appActivity,
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
            }
        }
    }
}
