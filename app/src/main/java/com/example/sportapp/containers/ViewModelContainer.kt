package com.example.sportapp.containers

import AppActivityViewModel
import com.example.sportapp.models.viewModels.NewsActivityViewModel

data class ViewModelContainer(
    val appActivity: AppActivityViewModel,
//    val videoViewModel: YoutubeActivityViewModel,
    val newsViewModel: NewsActivityViewModel,
//    val matchesViewModel: MatchesActivitySoccerViewModel,
//    val authViewModel: AuthViewModel,
//    val aiViewModel: AIAnswerViewModel
)
