package com.example.sportapp.API.entities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MatchesActivityViewModel: ViewModel() {
    val state : MutableStateFlow<MatchState> = MutableStateFlow(MatchState.Load)
//    private val _matchesState = mutableStateOf<MatchResponse?>(null)
//    var matchesState by _matchesState

    init {
        loadMatches()
    }

    fun loadMatches() {
        viewModelScope.launch {

            val matches = fetchMatches()
            if (matches == null) {
                state.value = MatchState.Error(NullPointerException())
            } else {
                state.value = MatchState.MatchContent(matches = matches)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
//        viewModelScope.de
    }
}

sealed interface  MatchState {
    data object Load : MatchState
    data class Error(val e: Throwable): MatchState
    data class MatchContent(val matches: List<MatchItem>): MatchState
}