package com.example.sportapp.models.soccer.api.domain

data class Replacement (
    val teamId: String,
    val playerId: String,
    val playerSurname: String,
    val name: String,
    val playerFullName: String,
    val playerShirtName: String,
)