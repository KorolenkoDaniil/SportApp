package com.example.sportapp.api.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.domain.RankingEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RankingsActivityViewModel: ViewModel() {
    private val _state : MutableStateFlow<RankingsState> = MutableStateFlow(RankingsState.Load)

    private val _soccerRepository = SoccerRepository()

    fun getState(): StateFlow<RankingsState> {
        return _state
    }


    init {
        loadRankings()
    }

    fun loadRankings(){
        viewModelScope.launch {
            try {
                _state.value = RankingsState.RankingsContent(_soccerRepository.getRankings())
            }
            catch (e: Throwable){
                _state.value = RankingsState.Error(e)
            }
        }
    }
}

sealed interface RankingsState : BaseState{
    data object Load : RankingsState
    data class Error(val e: Throwable): RankingsState
    data class RankingsContent (val rankings: List<RankingEntity>): RankingsState
}