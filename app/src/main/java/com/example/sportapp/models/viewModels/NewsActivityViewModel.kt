package com.example.sportapp.models.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.news.NewsRepository
import com.example.sportapp.models.news.domain.NewsListEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsActivityViewModel : ViewModel(), NewsViewModelInterface<NewsSate> {

    var listOfLoadedNews = null

    override val state: MutableStateFlow<NewsSate> = MutableStateFlow(NewsSate.Load)

    override val newsRepository = NewsRepository()

    override fun getState(): StateFlow<NewsSate> {
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
                val pageWithNews = newsRepository.getNews(pageNumber)
                Log.d("tttNews", "${pageWithNews.pageNumber}  ${pageWithNews.totalPages}  ${pageWithNews.news}")

                state.value = NewsSate.NewsContent(pageWithNews)
            } catch (e: Throwable) {
                Log.e("tttNews", "Error loading news data: ${e.message}", e)
                state.value = NewsSate.Error(e)
            }
        }
    }
}

sealed interface NewsSate : BaseState {
    data object Load : NewsSate
    data class Error(val e: Throwable) : NewsSate
    data class NewsContent(val news: NewsListEntity) : NewsSate
}
