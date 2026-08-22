package com.example.sportapp.support2026.features.soccer.domain.repository

import com.example.sportapp.support2026.features.soccer.domain.entities.Match

interface SoccerDomainRepository {

    suspend fun getMatchData(matchId: Int) : Match

    suspend fun getLeagueMatches() : List<Match>?
}