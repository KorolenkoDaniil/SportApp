package com.example.sportapp.models.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.soccer.SoccerRepository
import com.example.sportapp.models.soccer.api.domain.RankingEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RankingsActivitySoccerViewModel: ViewModel(), SoccerViewModelInterface<RankingsState> {
    override val state : MutableStateFlow<RankingsState> = MutableStateFlow(RankingsState.Load)

    override val soccerRepository: SoccerRepository = SoccerRepository()

    override fun getState(): StateFlow<RankingsState> {
        return state
    }


    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch {
            try {
                state.value = RankingsState.RankingsContent(soccerRepository.getRankings())
            }
            catch (e: Throwable){
                state.value = RankingsState.Error(e)
            }
        }
    }
}

sealed interface RankingsState : BaseState {
    data object Load : RankingsState
    data class Error(val e: Throwable): RankingsState
    data class RankingsContent (val rankings: List<RankingEntity>): RankingsState
}