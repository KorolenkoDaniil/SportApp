package com.example.sportapp.models.viewModels

import com.example.sportapp.models.news.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface NewsViewModelInterface <T> : BaseViewModelInterface<T> {
    override val state: MutableStateFlow<T>

    val newsRepository: NewsRepository

    override fun getState(): StateFlow<T>

    override fun loadData()
}