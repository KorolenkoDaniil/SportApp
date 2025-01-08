package com.example.sportapp.api.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.domain.MatchEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MatchActivityViewModel : ViewModel(), ViewModelInterface<MatchState> {

    override val state: MutableStateFlow<MatchState> = MutableStateFlow(MatchState.Load)

    override val soccerRepository = SoccerRepository()

    override fun getState(): StateFlow<MatchState> {
        return state
    }

    init {
        loadData()
    }

    override fun loadData() { }

    fun loadMatchData(matchId: String) {
        MatchState.Load.also { state.value = it }
        viewModelScope.launch {
            try {
                Log.d("tttOneMatch", matchId)
                val match = soccerRepository.getMatch(matchId)
                state.value = MatchState.MatchContent(match = match)
            } catch (e: Throwable) {
                state.value = MatchState.Error(e)
                Log.d("tttOneMatch", e.message.toString())
            }
        }
    }
}

sealed interface MatchState : BaseState {
    data object Load : MatchState
    data class Error(val e: Throwable) : MatchState
    data class MatchContent(val match: MatchEntity) : MatchState
}