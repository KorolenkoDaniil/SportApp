package com.example.sportapp.support2026.features.user.data.dto.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckUserDto (
    @SerialName("email") val email: String,
)

//todo дописать проверку пользователя
//todo написать на сервер проверку
//todo тут сделать обновление пользователя