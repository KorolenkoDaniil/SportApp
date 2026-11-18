package com.example.sportapp.support2026.features.news.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.viewModels.BaseState
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails
import com.example.sportapp.support2026.features.news.domain.useCases.GetNewsDetailsUseCase
import com.example.sportapp.support2026.features.newsLike.domain.useCases.ToggleStatusUseCase
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
    private val getNewsDetailsUseCase: GetNewsDetailsUseCase,
    private val toggleStatusUseCase: ToggleStatusUseCase
) : ViewModel(){

    private val _state = MutableStateFlow<NewsDetailsState>(NewsDetailsState.Load)
    val state: StateFlow<NewsDetailsState> = _state.asStateFlow()
    private val _loadedNewsDetails = MutableStateFlow<NewsDetails?>(null)
    val loadedNewsDetails: StateFlow<NewsDetails?> = _loadedNewsDetails.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private var lastClickTime = 0L

    private val _isLiked = MutableStateFlow(false)
    val isLiked: StateFlow<Boolean> = _isLiked.asStateFlow()

    fun loadDetails(id: Int, email: String,) {
        viewModelScope.launch {
            if (_loading.value) return@launch

            try {
                _loading.value = true
                _state.value = NewsDetailsState.Load

                val details = getNewsDetailsUseCase(id, email)
                Log.d("tttNews", "$details")

                if (details == null) {
                    throw NullPointerException("Детали новости пустые")
                }

                _state.value = NewsDetailsState.NewsDetailsContent(details)

                _isLiked.value = details.isLiked

            } catch (e: Throwable) {
                _state.value = NewsDetailsState.Error(e)
                Log.e("tttNews", "Ошибка загрузки деталей: ${e.message}", e)
            } finally {
                _loading.value = false
            }
        }
    }




    fun toggleLike (
        currentNewsId: Int,
        email: String)
    {

        val previousState = _isLiked.value
        _isLiked.value = !previousState

        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < 500) {
            return
        }
        lastClickTime = currentTime



        viewModelScope.launch {
            try {
                val toggleResult = toggleStatusUseCase.invoke(
                    newsId = currentNewsId,
                    email = email
                )

                // Если сервер ответил false — откатываем назад
                if (!toggleResult) {
                    _isLiked.value = previousState
                    Log.e("tttNews", "Сервер отклонил лайк")
                }

            } catch (e: Throwable) {
                // При ошибке сети тоже возвращаем старое значение, не ломая весь экран
                _isLiked.value = previousState
                Log.e("tttNews", "Ошибка изменения состояния лайка: ${e.message}", e)
            }
        }

    }

}