package com.example.sportapp.api.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.api.SoccerRepository
import com.example.sportapp.domain.MatchDayEntity
import com.example.sportapp.domain.MatchEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class MatchesActivityViewModel : ViewModel(), ViewModelInterface<MatchesState> {

    lateinit var nearestMatch: MatchEntity

    override val state: MutableStateFlow<MatchesState> = MutableStateFlow(MatchesState.Load)

    override val soccerRepository = SoccerRepository()

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
                val matchDays = soccerRepository.getMatchDays()
                state.value = MatchesState.MatchesContent(matchDays = matchDays)
                nearestMatch = searchNearestMatch(matchDays)
            } catch (e: Throwable) {
                state.value = MatchesState.Error(e)
            }
        }
    }


    fun searchNearestMatch(matchDays: List<MatchDayEntity>): MatchEntity {

        val currentTime = ZonedDateTime.now()
        var nearestMatch = matchDays[0].matches[0]
        var nearestTimeDifference = Long.MAX_VALUE
        val combinedList = mutableListOf<MatchEntity>()

        for (day in matchDays) {
            combinedList += day.matches
        }

        for (day in matchDays) {
            for (match in day.matches) {

                val localMatchDateTime = match.localDateTimeMatchStart

                val timeDifference =
                    java.time.Duration.between(currentTime, localMatchDateTime).toMillis()

                if (timeDifference >= 0 && timeDifference < nearestTimeDifference) {
                    nearestMatch = match
                    nearestTimeDifference = timeDifference
                }
            }
        }

        Log.d("nearest Match", "${nearestMatch.teamAName}  ${nearestMatch.teamBName}")

        return nearestMatch
    }
}

sealed interface MatchesState : BaseState {
    data object Load : MatchesState
    data class Error(val e: Throwable) : MatchesState
    data class MatchesContent(val matchDays: List<MatchDayEntity>) : MatchesState
}
