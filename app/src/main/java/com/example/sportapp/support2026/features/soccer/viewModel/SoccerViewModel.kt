package com.example.sportapp.support2026.features.soccer.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.viewModels.BaseState
import com.example.sportapp.support2026.features.soccer.domain.entities.Match
import com.example.sportapp.support2026.features.soccer.domain.useCases.GetMatchByIdUseCase
import com.example.sportapp.support2026.features.soccer.domain.useCases.LoadLeagueMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MatchesListState : BaseState {
    data object Load : MatchesListState
    data class Error(val e: Throwable) : MatchesListState
    //    object используется для состояний, у которых нет данных.
    //    data class используется для состояний, которые несут данные.
    data class MatchesContent(val matches: List<Match>) : MatchesListState
}

@HiltViewModel
class SoccerViewModel @Inject constructor(
    private val loadLeagueMatchesUseCase: LoadLeagueMatchesUseCase,
    private val getMatchByIdaUseCase: GetMatchByIdUseCase
) : ViewModel() {

    private val _matchesUiState: MutableStateFlow<MatchesListState> =
        MutableStateFlow(MatchesListState.Load)
    val matchesUiState: StateFlow<MatchesListState> = _matchesUiState.asStateFlow()

    init {
        loadMatches()
    }

    fun loadMatches() {
        if (_matchesUiState.value is MatchesListState.Load && viewModelScope.coroutineContext.isActive.not()) {
            return
        }

        viewModelScope.launch {
            _matchesUiState.value = MatchesListState.Load
            try {
                val matches = loadLeagueMatchesUseCase.LoadLeagueMatches()
                _matchesUiState.value = MatchesListState.MatchesContent(matches = matches)
            } catch (e: Throwable) {
                Log.d("tttMatches", e.message.toString())
                _matchesUiState.value = MatchesListState.Error(e)
            }
        }
    }
}