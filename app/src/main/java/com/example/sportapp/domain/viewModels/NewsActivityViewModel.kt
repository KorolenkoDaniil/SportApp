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

    override fun loadData() {}

    private var _sportIndex: Int = -1

    var sportIndex: Int
        get() = _sportIndex
        set(value) {
            _sportIndex = value
        }



    private var _selectedNews = selectedNews

    var selectedNews: NewsEntity
        get() = _selectedNews
        set(value) {
            _selectedNews = value
        }


    private val _isSearched = MutableStateFlow(false)

    val isSearched: StateFlow<Boolean> = _isSearched

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


    fun toggleIsSearched (value: Boolean) {
        _isSearched.value = value
    }


    suspend fun loadNewsData(pageNumber: Int): List<NewsEntity> {
        toggleIsSearched(false)
        return repository.getNews(pageNumber).news
    }

    suspend fun searchNewsSuspend(
        pageNumber: Int,
        searchPrompt: String,
        sportIndex: Int,
    ): NewsListEntity? {
        return try {
            toggleIsSearched(true)
            repository.getNewsWithSearch(pageNumber, searchPrompt, sportIndex)
        } catch (e: Throwable) {
            toggleIsSearched(false)
            Log.e("search", "Ошибка поиска: ${e.message}", e)
            null
        }
    }



}


sealed interface NewsState : BaseState {
    data object Load : NewsState
    data class Error(val e: Throwable) : NewsState
    data class NewsContent(val news: NewsListEntity) : NewsState
}
