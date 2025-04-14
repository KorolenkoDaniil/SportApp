package com.example.sportapp.domain.viewModels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.LikeRepository
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.models.viewModels.BaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class LikeViewModel : ViewModel() {

    val state: MutableStateFlow<LikeState> = MutableStateFlow(LikeState.NotLiked)

    val repository = LikeRepository()

    fun putLike(newsDateTime: LocalDateTime, userEmail: String) {
        viewModelScope.launch {
            state.value = LikeState.Liked
            repository.putLike(newsDateTime, userEmail)
        }
    }

    fun removeLike(newsDateTime: LocalDateTime, userEmail: String) {
        viewModelScope.launch {
            state.value = LikeState.NotLiked
            repository.removeLike(newsDateTime, userEmail)
        }
    }

    suspend fun LikeExist(newsDateTime: LocalDateTime, userEmail: String): Boolean {
        return repository.LikeExist(newsDateTime, userEmail)
    }


    fun toggleLike(
        likeCoolDown: Long,
        lastLikeTime: MutableState<Long>,
        likeCount: MutableState<Int>,
        currentNews: NewsEntity,
        user: UserEntity,
    ) {
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastLikeTime.value >= likeCoolDown) {
            viewModelScope.launch {
                if (state.value == LikeState.Liked) {
                    removeLike(currentNews.dateTime, user.email)
                    likeCount.value -= 1
                    state.value = LikeState.NotLiked
                } else {
                    putLike(currentNews.dateTime, user.email)
                    likeCount.value += 1
                    state.value = LikeState.Liked
                }
                lastLikeTime.value = currentTime
            }
        }
    }

}

    sealed interface LikeState : BaseState {
    data object Liked : LikeState
    data object NotLiked : LikeState

}