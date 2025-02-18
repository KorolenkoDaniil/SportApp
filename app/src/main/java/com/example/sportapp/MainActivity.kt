package com.example.sportapp

import AppActivityViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

            val appActivity: AppActivityViewModel by viewModels()

            val newsViewModel: NewsActivityViewModel by viewModels()
            val newsState by newsViewModel.getState().collectAsState()

            val matchesViewModel: MatchesActivitySoccerViewModel by viewModels()
            val state by matchesViewModel.getState().collectAsState()

            val videoViewModel: YoutubeActivityViewModel by viewModels()
            val videoState by videoViewModel.getState().collectAsState()

            val navController = rememberNavController()

            val authViewModel: AuthViewModel by viewModels()
            val authState by authViewModel.authState.collectAsState()

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
                authState
            )
        }
    }
}

