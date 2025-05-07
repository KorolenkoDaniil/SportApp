package com.example.sportapp.models.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
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

    val newsList = mutableStateListOf<NewsEntity>()


    //TODO использовать этот метод
    fun addNews(news: NewsEntity) {
        newsList.add(news)
    }

    private var _selectedNews = selectedNews

    var selectedNews: NewsEntity
        get() = _selectedNews
        set(value) {
            _selectedNews = value
        }

    private var _sportIndexs = -1

    var sportIndex : Int
        get() = _sportIndexs
        set(value) {
            _sportIndexs = value
        }


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


     private suspend fun searchNewsSuspend(
        pageNumber: Int,
        searchPrompt: String,
        sportIndex: Int,
    ): NewsListEntity? {
        return try {
            repository.getNewsWithSearch(pageNumber, searchPrompt, sportIndex)
        } catch (e: Throwable) {
            Log.e("search", "Ошибка поиска: ${e.message}", e)
            null
        }
    }


    fun searchAndSetNews(
        pageNumber: Int,
        searchPrompt: String,
        sportIndex: Int,
        itemList: SnapshotStateList<NewsEntity>,
        clearElements: Boolean
    ) {
        viewModelScope.launch {
            val result = searchNewsSuspend(pageNumber, searchPrompt, sportIndex)
            result?.news?.let {
                if (clearElements){
                    itemList.clear()
                }
                itemList.addAll(it)
            }
        }
    }


}


sealed interface NewsState : BaseState {
    data object Load : NewsState
    data class Error(val e: Throwable) : NewsState
    data class NewsContent(val news: NewsListEntity) : NewsState
}
