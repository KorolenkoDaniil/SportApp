package com.example.sportapp.api.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.domain.MatchDayEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MatchesActivityViewModel : ViewModel() {
    val state: MutableStateFlow<MatchState> = MutableStateFlow(MatchState.Load)

    var openedMatchDay: MutableStateFlow<Int> = MutableStateFlow(0)

    fun changeMatchDay (newPage: Int) {
        openedMatchDay.value = newPage
    }


    val soccerRepository = SoccerRepository()

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