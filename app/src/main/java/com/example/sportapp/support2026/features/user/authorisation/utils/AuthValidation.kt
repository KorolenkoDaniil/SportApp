package com.example.sportapp.support2026.features.user.authorisation.utils


object AuthValidation {

    fun checkEmail(email: String): Boolean {
        if (email.isEmpty()) {
            return false
        }
        return true
    }

    fun checkPassword(password: String): Boolean {
        if (password.isEmpty()) {
            return false
        }
        return true
    }
}