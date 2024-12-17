package com.example.sportapp

import BottomBar
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.api.viewModels.MainViewModel
import com.example.sportapp.pages.HomePage
import com.example.sportapp.pages.LikePage
import com.example.sportapp.pages.MatchesPage
import com.example.sportapp.pages.Rankings
import com.example.sportapp.pages.VideoPage

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Matches : Screen("matches")
    object Video : Screen("video")
    object Like : Screen("like")
    object Rankings : Screen("rankings")
}

class mainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val mainViewmodel = MainViewModel()
            val navController = rememberNavController()

            Scaffold(
                containerColor = Color(0xFFF6F6F6),
                bottomBar = {
                    BottomBar(
                        navController = navController,
                    )
                },
            ) { innerPadding ->

                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 8.dp)
                ) {
                    composable(Screen.Home.route) { HomePage() }
                    composable(Screen.Matches.route) { MatchesPage() }
                    composable(Screen.Video.route) { VideoPage() }
                    composable(Screen.Like.route) { LikePage() }
                    composable(Screen.Rankings.route) { Rankings() }
                }
            }
        }
    }
}
