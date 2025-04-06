package com.example.sportapp

import AppActivityViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.models.viewModels.AIAnswerViewModel
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.pagesAndWidgets.pages.Screen
import com.example.sportapp.pagesAndWidgets.pages.MyAppNavigation as MyAppNavigation1


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
            val state by matchesViewModel.getState().collectAsState()


            val navController = rememberNavController()

            val authViewModel: AuthViewModel by viewModels()
            val authState by authViewModel.authState.collectAsState()

            val AIViewModel: AIAnswerViewModel by viewModels()
            val answerState by AIViewModel.getState().collectAsState()


            val url = intent.data

            var openingViaDeepLink = false;

            val startDestination = if (url != null && url.scheme == "korsport" && url.host == "news") {
                openingViaDeepLink = true

                val newsId = url.lastPathSegment ?: ""
                "news/$newsId"
            } else {
                Screen.FirstPage.route
            }


            MyAppNavigation1(
                authViewModel = authViewModel,
                newsState = newsState,
                state = state,
                videoState = videoState,
                newsViewModel = newsViewModel,
                appActivity = appActivity,
                navController = navController,
                matchesViewModel = matchesViewModel,
                videoViewModel = videoViewModel,
                authState = authState,
                AIViewModel,
                answerState,
                startDestination,
                openingViaDeepLink
            )
        }
    }
}

