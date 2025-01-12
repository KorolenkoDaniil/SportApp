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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.models.viewModels.MatchesActivityViewModel
import com.example.sportapp.models.viewModels.VideosActivityViewModel
import com.example.sportapp.pages.HomePage
import com.example.sportapp.pages.LikePage
import com.example.sportapp.pages.MatchesPage
import com.example.sportapp.pages.VideoPage

// класс, который определяет текущий экран

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Matches : Screen("matches")
    object Video : Screen("video")
    object Like : Screen("like")
}


class mainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val appActivity: AppActivityViewModel = viewModel()


            val matchesViewModel: MatchesActivityViewModel = viewModel()
            val state by matchesViewModel.getState().collectAsState()

            val videoViewModel: VideosActivityViewModel = viewModel()
            val videoState by videoViewModel.getState().collectAsState()


            val navController = rememberNavController()

            Scaffold(
                containerColor = Color(0xFFF6F6F6),

                //нижняяя навигационная панель
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },

                //верхняя панель
                topBar = { TopAppBar(appActivity) }


            ) { innerPadding ->

                //контролер навигации в приложжениии и пути навигации
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 8.dp)
                ) {
                    composable(Screen.Home.route) { HomePage(state, videoState, matchesViewModel, videoViewModel, appActivity)}
                    composable(Screen.Matches.route) { MatchesPage(matchesViewModel, state, appActivity) }
                    composable(Screen.Video.route) { VideoPage(appActivity) }
                    composable(Screen.Like.route) { LikePage(appActivity) }
                }
            }
        }
    }
}



