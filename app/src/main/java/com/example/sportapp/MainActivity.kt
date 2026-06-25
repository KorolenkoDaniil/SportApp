package com.example.sportapp

//import com.example.sportapp.domain.viewModels.authorization.AuthViewModel


import AppActivityViewModel
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.compose.rememberNavController
//import com.example.sportapp.containers.StatesContainer
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.presentation.navigation.MyAppNavigation
import com.example.sportapp.presentation.navigation.Screen
import com.example.sportapp.ui.theme.AppTheme

val LocalErrorHandler = staticCompositionLocalOf<(Throwable) -> Unit> { {} }


class MainActivity : ComponentActivity() {

    private val appActivity: AppActivityViewModel by viewModels()
//    private val videoViewModel: YoutubeActivityViewModel by viewModels()
    private val newsViewModel: NewsActivityViewModel by viewModels()
//    private val matchesViewModel: MatchesActivitySoccerViewModel by viewModels()
//    private val authViewModel: AuthViewModel by viewModels()
//    private val aiViewModel: AIAnswerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Composable", "Composable  mainActivity1")

        setContent {

//            val videoState by videoViewModel.getState().collectAsState()
            val newsState by newsViewModel.getState().collectAsState()
//            val matchesState by matchesViewModel.getState().collectAsState()
//            val authState by authViewModel.authState.collectAsState()

            AppTheme(
//                authViewModel = authViewModel
            ) {

                val viewModels = ViewModelContainer(
                    appActivity,
//                    videoViewModel,
                    newsViewModel,
//                    matchesViewModel,
//                    authViewModel,
//                    aiViewModel
                )

//                val states = StatesContainer(
////                    videoState,
//                    newsState,
////                    matchesState,
////                    authState
//                )

                val navController = rememberNavController()
                val url = intent.data
//                val startDestination = if (url != null && url.scheme == "korsport" && url.host == "news") {
//                    val newsId = url.lastPathSegment ?: ""
//                    "news/$newsId"
//                } else {
//                    Screen.FirstPage.route
//                }


                val startDestination = Screen.LoadingPage.route

                Log.d("Composable", "Composable  mainActivity2")

                MyAppNavigation(
                    viewModels = viewModels,
//                    states = states,
                    navController = navController,
                    startDestination = startDestination,
                )
            }
        }

    }
}

