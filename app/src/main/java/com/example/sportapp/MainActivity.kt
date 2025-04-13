package com.example.sportapp

import AppActivityViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.containers.StatesContainer
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.models.viewModels.AIAnswerViewModel
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.presentation.navigation.Screen
import com.example.sportapp.presentation.navigation.MyAppNavigation as MyAppNavigation1


class mainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val appActivity: AppActivityViewModel by viewModels()

            val videoViewModel: YoutubeActivityViewModel by viewModels()
            val videoState by videoViewModel.getState().collectAsState()

            val newsViewModel: NewsActivityViewModel by viewModels()
            val newsState by newsViewModel.getState().collectAsState()

            val matchesViewModel: MatchesActivitySoccerViewModel by viewModels()
            val matchesState by matchesViewModel.getState().collectAsState()

            val authViewModel: AuthViewModel by viewModels()
            val authState by authViewModel.authState.collectAsState()

            val aiViewModel: AIAnswerViewModel by viewModels()
            val answerState by aiViewModel.getState().collectAsState()

            val viewModels = ViewModelContainer(
                appActivity,
                videoViewModel,
                newsViewModel,
                matchesViewModel,
                authViewModel,
                aiViewModel
            )

            val states = StatesContainer(
                videoState,
                newsState,
                matchesState,
                authState,
                answerState
            )

            val navController = rememberNavController()

            val url = intent.data

            val startDestination = if (url != null && url.scheme == "korsport" && url.host == "news") {

                val newsId = url.lastPathSegment ?: ""
                "news/$newsId"
            } else {
                Screen.FirstPage.route
            }


            MyAppNavigation1(
                viewModels,
                states,
                navController = navController,
                startDestination,
            )
        }
    }
}

