package com.example.sportapp.domain.viewModels.authorization

import com.example.sportapp.CleanArchitexture.data.repositories.UserRepository
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.domain.viewModels.authorization.utils.AuthorizationUtils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await

class LoginUseCase {

    suspend fun login(
        _authState: MutableStateFlow<AuthState>,
        auth: FirebaseAuth,
        email: String,
        password: String,
        authorizationUtils: AuthorizationUtils,
        userRep: UserRepository,
        _currentUser: MutableStateFlow<UserEntity?>,
        _themeIsWhite: MutableStateFlow<Boolean>
    ) {
        _authState.value = try {
            auth.signInWithEmailAndPassword(email, password).await()
            authorizationUtils.updateCurrentUser(
                 userRep.getUser(auth.currentUser!!.email!!),
                _currentUser,
                _themeIsWhite
            )

            AuthState.Authenticated
        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Login failed")
        }
    }
}