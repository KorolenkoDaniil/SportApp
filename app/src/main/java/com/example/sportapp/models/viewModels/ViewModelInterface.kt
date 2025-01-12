package com.example.sportapp.api.viewModels

import com.example.sportapp.models.soccer.SoccerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ViewModelInterface <T> {
    val state: MutableStateFlow<T>

    val soccerRepository: SoccerRepository

    fun getState(): StateFlow<T>

    fun loadData()
}