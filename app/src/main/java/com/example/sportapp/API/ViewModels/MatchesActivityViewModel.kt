package com.example.sportapp.API.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.API.entities.MatchDayEntity
import com.example.sportapp.API.entities.MatchItem
import com.example.sportapp.API.entities.fetchMatches
import com.example.sportapp.API.entities.matchDaysList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MatchesActivityViewModel: ViewModel() {
    val state : MutableStateFlow<MatchState> = MutableStateFlow(MatchState.Load)

//    val matchDays : MutableStateFlow<List<MatchDayEntity>> =

    init {
        loadMatches()
    }

    private fun loadMatches() {
        viewModelScope.launch {
            val matches = fetchMatches()
            if (matches == null) {
                state.value = MatchState.Error(NullPointerException())
            } else {
                state.value = MatchState.MatchContent(matches = matches)
                Log.d("ttt1", matches.toString())
                matchDays.value = matchDaysList(matches)
            }
        }
    }
}

sealed interface  MatchState {
    data object Load : MatchState
    data class Error(val e: Throwable): MatchState
    data class MatchContent(val matches: List<MatchDayEntity>): MatchState
}