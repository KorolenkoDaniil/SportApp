package com.example.sportapp.support2026.features.soccer.domain.useCases

import android.util.Log
import com.example.sportapp.support2026.features.soccer.domain.entities.Match
import com.example.sportapp.support2026.features.soccer.domain.repository.SoccerDomainRepository
import javax.inject.Inject


class LoadLeagueMatchesUseCase @Inject constructor(
    private val repository: SoccerDomainRepository
) {

    suspend fun LoadLeagueMatches( ): List<Match>? {
        return try {
            repository.getLeagueMatches()
        } catch (e: Exception) {
            Log.d("exception", "${e.message}")
            null
        }
    }
}