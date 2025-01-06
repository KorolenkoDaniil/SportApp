package com.example.sportapp

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
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.api.viewModels.MatchesState
import com.example.sportapp.pages.HomePage
import com.example.sportapp.pages.LikePage
import com.example.sportapp.pages.MatchesPage
import com.example.sportapp.pages.VideoPage
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

            val mainViewModel: MatchesActivityViewModel = viewModel()
            val state by mainViewModel.getState().collectAsState()

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
                    composable(Screen.Home.route) { HomePage(state, mainViewModel) }
                    composable(Screen.Matches.route) { MatchesPage(mainViewModel, state) }
                    composable(Screen.Video.route) { VideoPage() }
                    composable(Screen.Like.route) { LikePage() }
                }
            }
        }
    }
}



