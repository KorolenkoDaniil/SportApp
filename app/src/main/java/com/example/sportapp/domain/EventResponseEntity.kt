package com.example.sportapp.domain

data class EventResponseEntity(
    val type: String,
    val matchPhase: String?,
    val order: Int,
    val id: String,
    val teamId: String?,
    val playerId: String?,
    val playerSurname: String?,
    val name: String?,
    val playerFullName: String?,
    val playerShirtName: String?,
    val minute: Int?
)