package com.example.sportapp.models.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.NewsRepository
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsListEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsActivityViewModel : ViewModel(), BaseViewModelInterface<NewsState, NewsRepository> {

    override val state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.Load)
    override val repository: NewsRepository = NewsRepository()

    override fun getState(): StateFlow<NewsState> = state

    override fun loadData() { }

    init {
        viewModelScope.launch {
            try {
                val newsListEntity = repository.getNews(pageNumber = 1)
                state.value = NewsState.NewsContent(newsListEntity)
            } catch (e: Throwable) {
                Log.e("tttNews", "Ошибка при загрузке новостей: ${e.message}", e)
                state.value = NewsState.Error(e)
            }
        }
    }


    suspend fun loadNewsData(pageNumber: Int): List<NewsEntity> {
        return  repository.getNews(pageNumber).news
    }
}

sealed interface NewsState : BaseState {
    data object Load : NewsState
    data class Error(val e: Throwable) : NewsState
    data class NewsContent(val news: NewsListEntity) : NewsState
}
