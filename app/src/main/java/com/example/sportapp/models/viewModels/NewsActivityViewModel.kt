package com.example.sportapp.models.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.news.NewsRepository
import com.example.sportapp.models.news.domain.NewsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsActivityViewModel : ViewModel(), NewsViewModelInterface<NewsSate> {
    override val state: MutableStateFlow<NewsSate> = MutableStateFlow(NewsSate.Load)

    override val newsRepository = NewsRepository()

    override fun getState(): StateFlow<NewsSate> {
        return state
    }

    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch {
            try {
                val news = newsRepository.getNews()
                state.value = NewsSate.NewsContent(news)
            } catch (e: Throwable) {
                state.value = NewsSate.Error(e)
            }
        }
    }
}

sealed interface NewsSate : BaseState {
    data object Load : NewsSate
    data class Error(val e: Throwable) : NewsSate
    data class NewsContent(val news: List<NewsEntity>) : NewsSate
}
