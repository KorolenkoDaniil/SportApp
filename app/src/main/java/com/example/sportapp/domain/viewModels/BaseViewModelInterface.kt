package com.example.sportapp.models.viewModels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface BaseViewModelInterface<TState, TRepository> {
    val state: MutableStateFlow<TState>

    val repository: TRepository

    fun getState(): StateFlow<TState> = state

    fun loadData()
}
