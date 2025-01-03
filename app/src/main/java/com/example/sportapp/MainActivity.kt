package com.example.sportapp

import BottomNavBar
import TopAppBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

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
                topBar = { TopAppBar() }


            ) { innerPadding ->

                //контролер навигации в приложжениии и пути навигации
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
                }
            }
        }
    }
}
