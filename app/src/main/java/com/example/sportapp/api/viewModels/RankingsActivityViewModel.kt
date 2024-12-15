package com.example.sportapp.api.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.domain.RankingEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RankingsActivityViewModel: ViewModel() {
    val state : MutableStateFlow<RankingsState> = MutableStateFlow(RankingsState.Load)

    val soccerRepository = SoccerRepository()

    init {
        loadRankings()
    }

    fun loadRankings(){
        viewModelScope.launch {
            try {
                val rankings = soccerRepository.getRankings()
                Log.d("rankings", rankings.toString())
                state.value = RankingsState.RankingsContent(soccerRepository.getRankings())
            }
            catch (e: Throwable){
                state.value = RankingsState.Error(e)
            }
        }
    }
}

sealed interface RankingsState{
    data object Load : RankingsState
    data class Error(val e: Throwable): RankingsState
    data class RankingsContent (val rankings: List<RankingEntity>): RankingsState
}