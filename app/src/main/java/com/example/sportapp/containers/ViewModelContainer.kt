package com.example.sportapp.containers

import AppActivityViewModel
import com.example.sportapp.models.viewModels.AIAnswerViewModel
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel

data class ViewModelContainer(
    val appActivity: AppActivityViewModel,
    val videoViewModel: YoutubeActivityViewModel,
    val newsViewModel: NewsActivityViewModel,
    val matchesViewModel: MatchesActivitySoccerViewModel,
    val authViewModel: AuthViewModel,
    val aiViewModel: AIAnswerViewModel
)
