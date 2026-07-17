package com.example.sportapp.support2026.features.news.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.viewModels.BaseState
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails
import com.example.sportapp.support2026.features.news.domain.useCases.GetNewsDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface NewsDetailsState : BaseState {
    data object Load : NewsDetailsState
    data class Error(val e: Throwable) : NewsDetailsState
    data class NewsDetailsContent(val newsDetails: NewsDetails) : NewsDetailsState
}

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val getNewsDetailsUseCase: GetNewsDetailsUseCase
) : ViewModel(){

    private val _state = MutableStateFlow<NewsDetailsState>(NewsDetailsState.Load)
    val state: StateFlow<NewsDetailsState> = _state.asStateFlow()
    private val _loadedNewsDetails = MutableStateFlow<NewsDetails?>(null)
    val loadedNewsDetails: StateFlow<NewsDetails?> = _loadedNewsDetails.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun loadDetails(id: Int) {
        viewModelScope.launch {
            if (_loading.value) return@launch

            try {
                _loading.value = true
                _state.value = NewsDetailsState.Load

                val details = getNewsDetailsUseCase(id)
                Log.d("tttNews", "$details")

                if (details == null) {
                    throw NullPointerException("Детали новости пустые")
                }

                _state.value = NewsDetailsState.NewsDetailsContent(details)

            } catch (e: Throwable) {
                _state.value = NewsDetailsState.Error(e)
                Log.e("tttNews", "Ошибка загрузки деталей: ${e.message}", e)
            } finally {
                _loading.value = false
            }
        }
    }
    
}