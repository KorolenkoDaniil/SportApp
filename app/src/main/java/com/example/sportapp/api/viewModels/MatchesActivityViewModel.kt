package com.example.sportapp.api.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.domain.MatchDayEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchesActivityViewModel : ViewModel(), ViewModelInterface<MatchState> {

    override val state: MutableStateFlow<MatchState> = MutableStateFlow(MatchState.Load)

    override val soccerRepository = SoccerRepository()

    override fun getState(): StateFlow<MatchState> {
        return state
    }

    init {
        loadData()
    }

    override fun loadData() {
        MatchState.Load.also { state.value = it }
        viewModelScope.launch {
            try {
                val matchDays = soccerRepository.getMatchDays()
                state.value = MatchState.MatchContent(matchDays = matchDays)
            } catch (e: Throwable) {
                state.value = MatchState.Error(e)
            }
        }
    }
}

sealed interface MatchState : BaseState {
    data object Load : MatchState
    data class Error(val e: Throwable) : MatchState
    data class MatchContent(val matchDays: List<MatchDayEntity>) : MatchState
}