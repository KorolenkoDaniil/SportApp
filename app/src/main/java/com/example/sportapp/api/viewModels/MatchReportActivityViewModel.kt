package com.example.sportapp.api.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.domain.EventEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchReportActivityViewModel: ViewModel(), ViewModelInterface<MatchReportState> {

    override val state : MutableStateFlow<MatchReportState> = MutableStateFlow(MatchReportState.Load)

    override val soccerRepository = SoccerRepository()

    override fun getState(): StateFlow<MatchReportState> {
        return state
    }

    override fun loadData () { }

    fun loadMatchReport(matchId: String){

        Log.d("tttMatchReport", "MatchReportActivityViewModel: вызов loadMatchReport с matchId: $matchId")

        viewModelScope.launch {
            val a = soccerRepository.getMatchReport(matchId)

            Log.d("tttMatchReport", "aaaa $a.toString()")
            try {
                state.value = MatchReportState.RankingsContent(soccerRepository.getMatchReport(matchId))
            }
            catch (e: Throwable){
                Log.d("tttMatchReport", e.message.toString())
                state.value = MatchReportState.Error(e)
            }
        }
    }
}

sealed interface MatchReportState : BaseState{
    data object Load : MatchReportState
    data class Error(val e: Throwable): MatchReportState
    data class RankingsContent (val rankings: List<EventEntity>): MatchReportState
}