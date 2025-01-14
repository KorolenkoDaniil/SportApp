package com.example.sportapp.models.viewModels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface BaseViewModelInterface <T>  {
    val state: MutableStateFlow<T>

    fun getState(): StateFlow<T>

    fun loadData()
}