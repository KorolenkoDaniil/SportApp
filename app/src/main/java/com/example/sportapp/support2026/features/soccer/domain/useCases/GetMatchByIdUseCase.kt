package com.example.sportapp.support2026.features.soccer.domain.useCases

import android.util.Log
import com.example.sportapp.support2026.features.soccer.domain.entities.Match
import com.example.sportapp.support2026.features.soccer.domain.repository.SoccerDomainRepository
import javax.inject.Inject

class GetMatchByIdUseCase @Inject constructor(
    private val repository: SoccerDomainRepository
) {
    suspend operator fun invoke (matchId: Int) : Match ? {
        return try {
            repository.getMatchData(matchId)
        } catch (e: Exception) {
            Log.d("tttMatch", "${e.message}")
            return null
        }
    }
}