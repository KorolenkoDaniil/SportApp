package com.example.sportapp.containers

import com.example.sportapp.models.viewModels.AnswerState
import com.example.sportapp.models.viewModels.AuthState
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.NewsState
import com.example.sportapp.models.viewModels.VideosState

data class StatesContainer(
    val videoState: VideosState,
    val newsState: NewsState,
    val matchesState: MatchesState,
    val authState: AuthState,
    val answerState: AnswerState
)
