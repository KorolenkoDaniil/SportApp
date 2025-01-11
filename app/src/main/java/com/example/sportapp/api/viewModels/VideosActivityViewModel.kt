package com.example.sportapp.api.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.api.entities.youtube.YoutubeSearchListResponseEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class VideosActivityViewModel: ViewModel(), ViewModelInterface<VideosState> {
    override val state : MutableStateFlow<VideosState> = MutableStateFlow(VideosState.Load)

    override val soccerRepository = SoccerRepository()

    override fun getState(): StateFlow<VideosState> {
        return state
    }


    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch {
            try {
                state.value = VideosState.VideosContent(soccerRepository.getVideos())
            }
            catch (e: Throwable){
                state.value = VideosState.Error(e)
            }
        }
    }
}

sealed interface VideosState : BaseState{
    data object Load : VideosState
    data class Error(val e: Throwable): VideosState
    data class VideosContent (val rankings: List<YoutubeSearchListResponseEntity>): VideosState
}