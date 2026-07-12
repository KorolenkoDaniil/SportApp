package com.example.sportapp.support2026.features.news.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.viewModels.BaseState
import com.example.sportapp.support2026.features.news.domain.entities.news.News
import com.example.sportapp.support2026.features.news.domain.useCases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface NewsState : BaseState {
    data object Load : NewsState
    data class Error(val e: Throwable) : NewsState
    data class NewsContent(val news: List<News>) : NewsState
}



@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel(){

    private val _state = MutableStateFlow<NewsState>(NewsState.Load)
    val state: StateFlow<NewsState> = _state.asStateFlow()
    private var _allLoadedNews = MutableStateFlow(listOf<News>())
    val allLoadedNews: StateFlow<List<News>> = _allLoadedNews.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()


    private var currentPage = 1

    init {
        loadNews(false)
    }

    fun loadNews(isNextPage: Boolean) {
        viewModelScope.launch {
            // 1. ЗАЩИТА: Если уже идет загрузка — игнорируем повторный вызов
            if (_loading.value) return@launch

            try {
                _loading.value = true
                if (isNextPage){
                    currentPage++
                }
                else{
                    _state.value = NewsState.Load
                    currentPage = 1
                    _allLoadedNews.value = emptyList()
                }
                val news = getNewsUseCase.invoke(currentPage)
                news.forEach { news ->
                    Log.d("tttNews","${news.dateTime} | ${news.title} vs ${news.text}")
                }
                _allLoadedNews.value = _allLoadedNews.value + news
                _state.value = NewsState.NewsContent(_allLoadedNews.value)
            } catch (e: Throwable) {

                _state.value = NewsState.Error(e)
                Log.d("tttNews", e.message.toString())
            }
            finally {
                _loading.value = false
            }
        }
    }
    
}