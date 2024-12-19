package com.example.sportapp.api.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.domain.EventResponseEntity
import com.example.sportapp.domain.RankingEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchReportActivityViewModel: ViewModel() {
    private val _state : MutableStateFlow<MatchReportState> = MutableStateFlow(MatchReportState.Load)

    private val _soccerRepository = SoccerRepository()

    fun getState(): StateFlow<MatchReportState> {
        return _state
    }


    fun loadMatchReport(matchId: String){
        viewModelScope.launch {
            try {
                _state.value = MatchReportState.RankingsContent(_soccerRepository.getMatchReport(matchId))
            }
            catch (e: Throwable){
                _state.value = MatchReportState.Error(e)
            }
        }
    }
}

sealed interface MatchReportState : BaseState{
    data object Load : MatchReportState
    data class Error(val e: Throwable): MatchReportState
    data class RankingsContent (val rankings: List<EventResponseEntity>): MatchReportState
}