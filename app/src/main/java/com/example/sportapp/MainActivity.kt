package com.example.sportapp

import AppActivityViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.models.user.AuthViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel


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

            val authViewModel: AuthViewModel by viewModels()

            MyAppNavigation(
                authViewModel = authViewModel,
                newsState = newsState,
                state = state,
                videoState = videoState,
                newsViewModel = newsViewModel,
                appActivity = appActivity,
                navController = navController,
                matchesViewModel = matchesViewModel,
                videoViewModel = videoViewModel,
                authViewModel.currentUser
            )
        }
    }
}

