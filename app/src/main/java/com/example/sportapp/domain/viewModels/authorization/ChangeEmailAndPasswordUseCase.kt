package com.example.sportapp.domain.viewModels.authorization

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class ChangeEmailAndPasswordUseCase () {

    suspend fun changeEmailAndPasswordFireBase(newPassword: String, auth: FirebaseAuth): String {
        val user = auth.currentUser
        return try {
            user!!.updatePassword(newPassword).await()

            "password updated successfully"
        } catch (e: Exception) {
            e.message ?: "Unknown error"
        }
    }

}