package com.example.sportapp.support2026.features.news.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
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
    private var currentPage = 1
//    private var allLoadedNews = mutableListOf<News>()
    private var allLoadedNews = listOf<News>()

//    fun getLoadedNews() : List<News> {
//        return allLoadedNews
//    }

    val loading = mutableStateOf(false)

    init {
        loadNews(false)
    }

    fun loadNews(isNextPage: Boolean) {

        loading.value = true

        viewModelScope.launch {
            try {

                if (isNextPage){
                    currentPage++
                }
                else{
                    _state.value = NewsState.Load
                    //только для 1 страницы показываем прогресс бар
//                    и включается состояние загрузки
                }

                val news = getNewsUseCase.invoke(currentPage)

                news.forEach { news ->
                    Log.d("tttNews","${news.dateTime} | ${news.title} vs ${news.text}")
                }

                allLoadedNews = allLoadedNews + news


                _state.value = NewsState.NewsContent(allLoadedNews)

                loading.value = false
            } catch (e: Throwable) {

                _state.value = NewsState.Error(e)
                Log.d("tttNews", e.message.toString())
            }
            finally {
                loading.value = false
            }
        }
    }

}