package com.example.sportapp.models.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.youtube.api.YoutubeRepository
import com.example.sportapp.models.youtube.domain.YoutubeSearchListResponseEntity
import com.example.sportapp.api.viewModels.ViewModelInterface
import com.example.sportapp.models.soccer.api.SoccerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class VideosActivityViewModel: ViewModel(), ViewModelInterface<VideosState> {
    override val state : MutableStateFlow<VideosState> = MutableStateFlow(VideosState.Load)

    override val soccerRepository: SoccerRepository = SoccerRepository()

    val youtubeRepository: YoutubeRepository = YoutubeRepository()

    override fun getState(): StateFlow<VideosState> {
        return state
    }


    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch {
            try {
                state.value = VideosState.VideosContent(youtubeRepository.getVideo())
            }
            catch (e: Throwable){
                state.value = VideosState.Error(e)
            }
        }
    }
}

sealed interface VideosState : BaseState {
    data object Load : VideosState
    data class Error(val e: Throwable): VideosState
    data class VideosContent (val rankings: List<YoutubeSearchListResponseEntity>): VideosState
}