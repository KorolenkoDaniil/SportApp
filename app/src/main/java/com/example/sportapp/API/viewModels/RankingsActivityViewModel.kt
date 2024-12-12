package com.example.sportapp.api.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RankingsActivityViewModel: ViewModel() {
    val state : MutableStateFlow<MatchState> = MutableStateFlow(MatchState.Load)

    val soccerRepository = SoccerRepository()

    init {
        loadRankings()
    }

    fun loadRankings(){
        viewModelScope.launch {
            try {
                val rankings = soccerRepository.getRankings()
                Log.d("rankings", "нормально")
            }
            catch (e: Throwable){
                state.value = MatchState.Error(e)
                Log.d("rankings", "говно")
            }
        }
    }
}

//sealed interface RankingState{
//    data object Load : MatchState
//    data class Error(val e: Throwable): MatchState
//    data class MatchContent (val matchDays: List<MatchDayEntity>): MatchState
//}