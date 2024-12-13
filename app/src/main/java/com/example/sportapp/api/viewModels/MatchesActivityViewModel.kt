package com.example.sportapp.api.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.domain.MatchDayEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MatchesActivityViewModel : ViewModel() {
    val state: MutableStateFlow<MatchState> = MutableStateFlow(MatchState.Load)

    private val _openedMatchDay: MutableStateFlow<Int> = MutableStateFlow(0)
    val openedMatchDay = _openedMatchDay.asStateFlow()

    val soccerRepository = SoccerRepository()

    fun setOpenedMatchDay(day: Int) {
        _openedMatchDay.value = day
    }


    init {
        loadMatches()
    }

    fun loadMatches() {
        state.value = MatchState.Load
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

sealed interface MatchState {
    data object Load : MatchState
    data class Error(val e: Throwable) : MatchState
    data class MatchContent(val matchDays: List<MatchDayEntity>) : MatchState
}