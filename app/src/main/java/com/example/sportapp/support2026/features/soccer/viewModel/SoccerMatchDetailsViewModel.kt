package com.example.sportapp.support2026.features.soccer.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.support2026.features.soccer.domain.entities.Match
import com.example.sportapp.support2026.features.soccer.domain.useCases.GetMatchByIdUseCase
import com.example.sportapp.support2026.presentation.BaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MatchDetailsState : BaseState {
    data object Load : MatchDetailsState
    data class Error(val e: Throwable) : MatchDetailsState
    //    object используется для состояний, у которых нет данных.
    //    data class используется для состояний, которые несут данные.
    data class MatchContent(val match: Match?) : MatchDetailsState
}

@HiltViewModel
class SoccerMatchDetailsViewModel @Inject constructor(
    private val getMatchByIdaUseCase: GetMatchByIdUseCase
) : ViewModel() {

    private val _matchDetailsUiState: MutableStateFlow<MatchDetailsState> =
        MutableStateFlow(MatchDetailsState.Load)
    val matchUiState: StateFlow<MatchDetailsState> = _matchDetailsUiState.asStateFlow()



    fun getMatchById(matchId: Int?) {
        if (matchId == null || matchId < 1)
            return

        if (_matchDetailsUiState.value is MatchDetailsState.Load && viewModelScope.coroutineContext.isActive.not()) {
            return
            //todo дописать нормальный возврат что не так
        }

        viewModelScope.launch {
            _matchDetailsUiState.value = MatchDetailsState.Load

            try {
                val match: Match? = getMatchByIdaUseCase.invoke(
                    matchId = matchId
                )
                _matchDetailsUiState.value = MatchDetailsState.MatchContent(match = match)
            } catch (e: Throwable) {
                Log.d("tttMatches", e.message.toString())
                _matchDetailsUiState.value = MatchDetailsState.Error(e)
            }
        }

    }
}