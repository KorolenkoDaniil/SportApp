package com.example.sportapp.models.viewModels

import com.example.sportapp.models.soccer.SoccerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface SoccerViewModelInterface <T> : BaseViewModelInterface<T> {
    override val state: MutableStateFlow<T>

    val soccerRepository: SoccerRepository

    override fun getState(): StateFlow<T>

    override fun loadData()
}