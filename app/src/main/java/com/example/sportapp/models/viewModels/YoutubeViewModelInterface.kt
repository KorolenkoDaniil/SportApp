package com.example.sportapp.models.viewModels

import com.example.sportapp.models.youtube.YoutubeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface YoutubeViewModelInterface <T> : BaseViewModelInterface<T> {
    override val state: MutableStateFlow<T>

    val youtubeRepository: YoutubeRepository

    override fun getState(): StateFlow<T>

    override fun loadData()
}