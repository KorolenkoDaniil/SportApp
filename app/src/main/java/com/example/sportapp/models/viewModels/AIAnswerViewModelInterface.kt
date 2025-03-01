package com.example.sportapp.models.viewModels

import com.example.sportapp.models.news.AIAnswerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface AIAnswerViewModelInterface <T> : BaseViewModelInterface<T> {
    override val state: MutableStateFlow<T>

    val AIAnswerRepository: AIAnswerRepository

    override fun getState(): StateFlow<T>

    override fun loadData()
}