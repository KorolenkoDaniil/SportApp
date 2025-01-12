package com.example.sportapp.models.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.viewModels.ViewModelInterface
import com.example.sportapp.models.soccer.SoccerRepository
import com.example.sportapp.models.youtube.YoutubeRepository
import com.example.sportapp.models.youtube.domain.YoutubeSearchListResponseEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideosActivityViewModel : ViewModel(), ViewModelInterface<VideosState> {
    override val state: MutableStateFlow<VideosState> = MutableStateFlow(VideosState.Load)

    override val soccerRepository: SoccerRepository
        get() {
            TODO()
        }


    private val youtubeRepository: YoutubeRepository = YoutubeRepository()

    override fun getState(): StateFlow<VideosState> {
        return state
    }

    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch {
            try {
                val videos = youtubeRepository.getVideos() // Assuming this returns List<VideoEntity>
                state.value = VideosState.VideosContent(videos)
            } catch (e: Throwable) {
                state.value = VideosState.Error(e)
            }
        }
    }
}

sealed interface VideosState : BaseState {
    data object Load : VideosState
    data class Error(val e: Throwable) : VideosState
    data class VideosContent(val videos: YoutubeSearchListResponseEntity) : VideosState
}
