package com.example.sportapp.models.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.SoccerRepository
import com.example.sportapp.CleanArchitexture.domain.models.match.EventEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchReportActivitySoccerViewModel: ViewModel(), BaseViewModelInterface <MatchReportState, SoccerRepository> {

    override val state : MutableStateFlow<MatchReportState> = MutableStateFlow(MatchReportState.Load)

    override val repository: SoccerRepository = SoccerRepository()

    override fun getState(): StateFlow<MatchReportState> {
        return state
    }

    override fun loadData () { }

    fun loadMatchReport(matchId: String){

        Log.d("tttMatchReport", "MatchReportActivityViewModel: вызов loadMatchReport с matchId: $matchId")

        viewModelScope.launch {
            val a = repository.getMatchReport(matchId)

            Log.d("tttMatchReport", "aaaa $a.toString()")
            try {
                state.value =
                    MatchReportState.RankingsContent(repository.getMatchReport(matchId))
            }
            catch (e: Throwable){
                Log.d("tttMatchReport", e.message.toString())
                state.value = MatchReportState.Error(e)
            }
        }
    }
}

sealed interface MatchReportState : BaseState {
    data object Load : MatchReportState
    data class Error(val e: Throwable): MatchReportState
    data class RankingsContent (val rankings: List<EventEntity>): MatchReportState
}