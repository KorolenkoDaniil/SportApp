package com.example.sportapp.models.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.SoccerRepository
import com.example.sportapp.CleanArchitexture.domain.models.match.MatchDayEntity
import com.example.sportapp.CleanArchitexture.domain.models.match.MatchEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class MatchesActivitySoccerViewModel : ViewModel(), BaseViewModelInterface <MatchesState, SoccerRepository> {

    lateinit var nearestMatch: MatchEntity

    override val state: MutableStateFlow<MatchesState> = MutableStateFlow(MatchesState.Load)

    override val repository: SoccerRepository = SoccerRepository()

    override fun getState(): StateFlow<MatchesState> {
        return state
    }

    init {
        loadData()
    }

    override fun loadData() {
        MatchesState.Load.also { state.value = it }
        viewModelScope.launch {
            try {
                val matchDays = repository.getMatchDays()
                state.value = MatchesState.MatchesContent(matchDays = matchDays)
                nearestMatch = searchNearestMatch(matchDays)
            } catch (e: Throwable) {
                state.value = MatchesState.Error(e)
            }
        }
    }


    private fun searchNearestMatch(matchDays: List<MatchDayEntity>): MatchEntity {

        var nearestMatch: MatchEntity? = null

        val currentTime = ZonedDateTime.now()
        var minimumTimeDifference = Long.MAX_VALUE

        for (day in matchDays) {

            val matchWithStatusOne = day.matches.find { it.matchStatus == 1 }
            if (matchWithStatusOne != null) {
                Log.d("Nearest Match", "Found match with status 1: ${matchWithStatusOne.teamAName} vs ${matchWithStatusOne.teamBName}")
                return matchWithStatusOne
            }

            for (matches in day.matches) {

                val matchTime = matches.localDateTimeMatchStart
                val timeDifference = java.time.Duration.between(currentTime, matchTime).toMillis()
                val absoluteTimeDifference = kotlin.math.abs(timeDifference)

                if (absoluteTimeDifference < minimumTimeDifference) {
                    nearestMatch = matches
                    minimumTimeDifference = absoluteTimeDifference
                }
            }
        }

        Log.d("Nearest Match", "${nearestMatch!!.teamAName} vs ${nearestMatch.teamBName}")

        
        return nearestMatch
    }
}

sealed interface MatchesState : BaseState {
    data object Load : MatchesState
    data class Error(val e: Throwable) : MatchesState
    data class MatchesContent(val matchDays: List<MatchDayEntity>) : MatchesState
}
