package com.example.sportapp.models.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.YoutubeRepository
import com.example.sportapp.CleanArchitexture.domain.models.youTube.VideoEntity
import com.example.sportapp.CleanArchitexture.domain.models.youTube.VideoPlayListResponseEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class YoutubeActivityViewModel : ViewModel(), BaseViewModelInterface <VideosState, YoutubeRepository> {
    override val state: MutableStateFlow<VideosState> = MutableStateFlow(VideosState.Load)

    override val repository = YoutubeRepository()

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
                val videos: VideoPlayListResponseEntity? = repository.getVideos()
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
        return repository.getVideos()?.items ?: emptyList()
    }

}


sealed interface VideosState : BaseState {
    data object Load : VideosState
    data class Error(val e: Throwable) : VideosState
    data class VideosContent(val videos: VideoPlayListResponseEntity) : VideosState
}