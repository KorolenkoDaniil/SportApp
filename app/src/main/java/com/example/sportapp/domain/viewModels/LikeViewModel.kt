package com.example.sportapp.domain.viewModels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.LikeRepository
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.models.viewModels.BaseState
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class LikeViewModel: ViewModel() {

    val repository = LikeRepository()

    fun putLike(newsDateTime: LocalDateTime, userEmail: String) {
        viewModelScope.launch {
            repository.putLike(
                newsDateTime = newsDateTime,
                userEmail = userEmail
            )
        }
    }

    fun removeLike(newsDateTime: LocalDateTime, userEmail: String) {
        viewModelScope.launch {
            repository.removeLike(
                newsDateTime = newsDateTime,
                userEmail = userEmail
            )
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
        isLiked: MutableState<Boolean>
    ) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastLikeTime.value >= likeCoolDown) {
            viewModelScope.launch {
                if (isLiked.value) {
                    removeLike(currentNews.dateTime, user.email)
                    likeCount.value -= 1
                } else {
                    putLike(currentNews.dateTime, user.email)
                    likeCount.value += 1
                }
                isLiked.value = !isLiked.value
                lastLikeTime.value = currentTime
            }
        }
    }
}

sealed interface LikeState : BaseState {
    data object Load : LikeState
    data class Error(val e: Throwable) : LikeState
}
