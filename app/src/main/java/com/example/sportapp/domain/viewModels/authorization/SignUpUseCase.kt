package com.example.sportapp.domain.viewModels.authorization

import android.util.Log
import com.example.sportapp.CleanArchitexture.data.repositories.UserRepository
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.domain.viewModels.authorization.utils.AuthorizationUtils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignUpUseCase {

    suspend fun signUp (
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
            auth.createUserWithEmailAndPassword(email, password).await()
            val user = withContext(Dispatchers.IO) {
                userRep.putNewUser(auth.currentUser?.email ?: "")
            }
            authorizationUtils.updateCurrentUser(user, _currentUser, _themeIsWhite)
            Log.d("currentUser", "User created successfully: $user")

//            appActivity.customChangeAppTheme(
//                isDark = !user.IsWhiteTheme
//            )

            AuthState.Authenticated

        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Signup failed")
        }
    }
}

