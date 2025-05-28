package com.example.sportapp.domain.viewModels.authorization.utils

import android.util.Log
import com.example.sportapp.CleanArchitexture.data.repositories.UserRepository
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.domain.viewModels.authorization.AuthState
import com.example.sportapp.domain.viewModels.authorization.ImageSubmissionState
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File

class AuthorizationUtils {

    fun updateCurrentUser(newUser: UserEntity?, _currentUser: MutableStateFlow<UserEntity?>, _themeIsWhite: MutableStateFlow<Boolean>) {
        _currentUser.value = newUser
        if (newUser != null) {
            Log.d("ttttheme", newUser.getIsWhiteTheme().toString())
            _themeIsWhite.value = newUser.getIsWhiteTheme()
        }
    }

    fun changeImageState (state: ImageSubmissionState, _imageState: MutableStateFlow<ImageSubmissionState>){
        _imageState.value = state
    }

    suspend fun uploadImageAndGetId(image: File, email: String, userRep: UserRepository): String {
        val response = userRep.uploadImage(imageFile = image, email = email)

        return if (response.status.isSuccess()) {
            val imageId = response.bodyAsText()
            println("Upload successful: $imageId")
            imageId
        } else {
            val error = "Upload failed: ${response.status}"
            println(error)
            throw Exception(error)
        }
    }

    fun checkEmailAndPassword(email: String, password: String, _authState: MutableStateFlow<AuthState>): Boolean{
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return false
        }
        else return true
    }
}