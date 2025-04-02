package com.example.sportapp.models.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.youtube.YoutubeRepository
import com.example.sportapp.models.youtube.domain.VideoEntity
import com.example.sportapp.models.youtube.domain.VideoPlayListResponseEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class YoutubeActivityViewModel : ViewModel(), YoutubeViewModelInterface<VideosState> {
    override val state: MutableStateFlow<VideosState> = MutableStateFlow(VideosState.Load)

    override val youtubeRepository = YoutubeRepository()

    override fun getState(): StateFlow<VideosState> {
        return state
    }

    private var _selectedVideo = selectedVideo

    var selectedVideo: VideoEntity
        get() = _selectedVideo
        set(value) {
            _selectedVideo = value
        }


    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch {
            try {
                val videos: VideoPlayListResponseEntity? = youtubeRepository.getVideos()
                if (videos != null) {
                    state.value = VideosState.VideosContent(videos)
                } else {
                    state.value = VideosState.Error(Exception("Failed to load videos"))
                }
            } catch (e: Throwable) {
                state.value = VideosState.Error(e)
            }
        }
    }

    suspend fun loadVideos(): List<VideoEntity> {
        return youtubeRepository.getVideos()?.items ?: emptyList()
    }

}


sealed interface VideosState : BaseState {
    data object Load : VideosState
    data class Error(val e: Throwable) : VideosState
    data class VideosContent(val videos: VideoPlayListResponseEntity) : VideosState
}