package com.example.sportapp.models.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.news.NewsRepository
import com.example.sportapp.models.news.domain.NewsEntity
import com.example.sportapp.models.news.domain.NewsListEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsActivityViewModel : ViewModel(), NewsViewModelInterface<NewsState> {

    var listOfLoadedNews = mutableListOf<NewsEntity>()

    override val state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.Load)

    override val NewsRepository: NewsRepository = NewsRepository()

    override fun getState(): StateFlow<NewsState> {
        return state
    }

    init {
        Log.d("tttNews", "выхван метод загрузки новостей")
        loadNewsData(1)
    }

    override fun loadData() {}

    fun loadNewsData(pageNumber: Int) {
        viewModelScope.launch {
            try {
                val pageWithNews = NewsRepository.getNews(pageNumber)
                Log.d("tttNews", "${pageWithNews.pageNumber}  ${pageWithNews.totalPages}  ${pageWithNews.news}")

                val listOfPages = NewsState.NewsContent(pageWithNews)

                listOfLoadedNews.addAll(listOfPages.news.news)
                state.value = listOfPages
            } catch (e: Throwable) {
                Log.e("tttNews", "Error loading news data: ${e.message}", e)
                state.value = NewsState.Error(e)
            }
        }
    }
}

sealed interface NewsState : BaseState {
    data object Load : NewsState
    data class Error(val e: Throwable) : NewsState
    data class NewsContent(val news: NewsListEntity) : NewsState
}
