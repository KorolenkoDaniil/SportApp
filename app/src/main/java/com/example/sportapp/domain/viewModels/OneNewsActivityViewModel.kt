package com.example.sportapp.models.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.NewsRepository
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class OneNewsActivityViewModel : ViewModel(), BaseViewModelInterface <OneNewsSate, NewsRepository> {
    override val state: MutableStateFlow<OneNewsSate> = MutableStateFlow(OneNewsSate.Load)

    override val repository = NewsRepository()

    override fun getState(): StateFlow<OneNewsSate> {
        return state
    }

    override fun loadData() {}


    fun loadOneNewsData(dateTime: String, email: String) {
        viewModelScope.launch {
            try {
                val oneNews = repository.getOneNews(dateTime, email)

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
