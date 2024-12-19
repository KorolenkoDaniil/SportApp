package com.example.sportapp.api.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.domain.MatchDayEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchesActivityViewModel : ViewModel() {

    private val _state: MutableStateFlow<MatchState> = MutableStateFlow(MatchState.Load)

    private val _soccerRepository = SoccerRepository()

    fun getState(): StateFlow<MatchState> {
        return _state
    }

    init {
        loadMatches()
    }

    fun loadMatches() {
        MatchState.Load.also { _state.value = it }
        viewModelScope.launch {
            try {
                val matchDays = _soccerRepository.getMatchDays()
                _state.value = MatchState.MatchContent(matchDays = matchDays)
            } catch (e: Throwable) {
                _state.value = MatchState.Error(e)
            }
        }
    }
}

sealed interface MatchState : BaseState {
    data object Load : MatchState
    data class Error(val e: Throwable) : MatchState
    data class MatchContent(val matchDays: List<MatchDayEntity>) : MatchState
}