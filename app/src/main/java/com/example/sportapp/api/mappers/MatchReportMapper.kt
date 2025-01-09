package com.example.sportapp.api.mappers

import EventResponse
import com.example.sportapp.domain.EventEntity
import com.example.sportapp.domain.Replacement

class MatchReportMapper {

    fun getMatchEventsList(matchEvents: List<EventResponse>): List<EventEntity> {
        val eventsList = matchEvents.map { event ->
            EventEntity(
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
                minute = event.minute ?: 0,
                additionalMinute = event.additionalMinute ?: 0,
                replacement = Replacement("", "", "", "", "", "")
            )
        }

        return eventMerging(eventsList)
    }


    private fun eventMerging(eventsList: List<EventEntity>): List<EventEntity> {

        val groupedByOrder = eventsList.groupBy { it.order }

        val mergedEvents = mutableListOf<EventEntity>()

        for ((_, events) in groupedByOrder) {
            val substitutionIn = events.find { it.type == "substitution_player_in" }
            val substitutionOut = events.find { it.type == "substitution_player_out" }

            if (substitutionIn != null && substitutionOut != null) {

                val mergedEvent = substitutionIn.copy(
                    type = "substitution_player",
                    replacement = Replacement(
                        teamId = substitutionOut.teamId,
                        playerId = substitutionOut.playerId,
                        playerSurname = substitutionOut.playerSurname,
                        name = substitutionOut.name,
                        playerFullName = substitutionOut.playerFullName,
                        playerShirtName = substitutionOut.playerShirtName
                    )
                )

                mergedEvents.add(mergedEvent)

            } else {
                mergedEvents.addAll(events)
            }
        }

        return mergedEvents
    }
}