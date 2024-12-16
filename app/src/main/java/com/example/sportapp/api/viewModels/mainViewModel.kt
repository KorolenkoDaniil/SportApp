package com.example.sportapp.api.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _currentPage = mutableStateOf("home")

    val currentPage: MutableState<String> get() = _currentPage

}