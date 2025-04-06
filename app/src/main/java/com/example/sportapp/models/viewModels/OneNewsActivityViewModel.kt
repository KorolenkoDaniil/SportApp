package com.example.sportapp.models.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.news.NewsRepository
import com.example.sportapp.models.news.domain.NewsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class OneNewsActivityViewModel : ViewModel(), NewsViewModelInterface<OneNewsSate> {
    override val state: MutableStateFlow<OneNewsSate> = MutableStateFlow(OneNewsSate.Load)

    override val NewsRepository = NewsRepository()

    override fun getState(): StateFlow<OneNewsSate> {
        return state
    }

    override fun loadData() {}


    fun loadOneNewsData(dateTime: String) {
        viewModelScope.launch {
            try {
                val oneNews = NewsRepository.getOneNews(dateTime)
                state.value = OneNewsSate.OneNewsContent(oneNews)
            } catch (e: Throwable) {
                state.value = OneNewsSate.Error(e)
            }
        }
    }
}

sealed interface OneNewsSate : BaseState {
    data object Load : OneNewsSate
    data class Error(val e: Throwable) : OneNewsSate
    data class OneNewsContent(val news: NewsEntity) : OneNewsSate
}
