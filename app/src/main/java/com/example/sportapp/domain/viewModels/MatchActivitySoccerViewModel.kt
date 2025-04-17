package com.example.sportapp.models.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.SoccerRepository
import com.example.sportapp.CleanArchitexture.domain.models.match.MatchEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MatchActivitySoccerViewModel : ViewModel(), BaseViewModelInterface<MatchState, SoccerRepository> {

    override val state: MutableStateFlow<MatchState> = MutableStateFlow(MatchState.Load)

    override val repository = SoccerRepository()

    override fun loadData() { }

    init {
        loadData()
    }

    fun loadMatchData(matchId: String) {
        MatchState.Load.also { state.value = it }
        viewModelScope.launch {
            try {
                Log.d("tttOneMatch", matchId)
                val match = repository.getMatch(matchId)
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