package com.example.sportapp.api.entities.ranking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastMatchResult (
    @SerialName("result") val result: String
)