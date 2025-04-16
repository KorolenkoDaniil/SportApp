package com.example.sportapp.domain.viewModels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.LikeRepository
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class LikeViewModel : ViewModel() {

    val repository = LikeRepository()

    fun putLike(newsDateTime: LocalDateTime, userEmail: String) {
        viewModelScope.launch {
            repository.putLike(newsDateTime, userEmail)
        }
    }

    fun removeLike(newsDateTime: LocalDateTime, userEmail: String) {
        viewModelScope.launch {
            repository.removeLike(newsDateTime, userEmail)
        }
    }


    fun toggleLike(
        lastLikeTime: MutableState<Long>,
        likeCount: MutableState<Int>,
        currentNews: NewsEntity,
        user: UserEntity,
    ) {
        val likeCoolDown = 2000L
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastLikeTime.value >= likeCoolDown) {
            viewModelScope.launch {
                if (currentNews.isLiked) {
                    removeLike(currentNews.dateTime, user.email)
                    likeCount.value -= 1
                } else {
                    putLike(currentNews.dateTime, user.email)
                    likeCount.value += 1
                }
                lastLikeTime.value = currentTime
                currentNews.isLiked = !currentNews.isLiked
            }
        }
    }

}
