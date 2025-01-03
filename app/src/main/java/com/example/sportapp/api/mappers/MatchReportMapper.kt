package com.example.sportapp.api.mappers

import EventResponse
import com.example.sportapp.domain.EventResponseEntity

class MatchReportMapper {

    fun getMatchEventsList(matchEvents: List<EventResponse>): List<EventResponseEntity> {
        return matchEvents.map { event ->
            EventResponseEntity(
                type = event.type ?: "unknown",
                matchPhase = event.matchPhase ?: "default_phase",
                order = event.order ?: 0,
                id = event.id ?: "0",
                teamId = event.teamId ?: "0",
                playerId = event.playerId ?: "0",
                playerSurname = event.playerSurname ?: "Unknown",
                name = event.name ?: "Unknown",
                playerFullName = event.playerFullName ?: "Unknown",
                playerShirtName = event.playerShirtName ?: "Unknown",
                minute = event.minute ?: 0
            )
        }
    }
}