package com.example.sportapp.support2026.features.user.viewModel

import androidx.lifecycle.ViewModel
import com.example.sportapp.support2026.features.user.domain.entity.User
import com.example.sportapp.support2026.features.user.domain.useCases.AddUserToDbUseCase
import com.example.sportapp.support2026.features.user.domain.useCases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
        private val getUserUseCase: GetUserUseCase,
        private val addUserUseCase: AddUserToDbUseCase
    ) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun updateCurrentUser () {

    }




//    fun checkUserState() {
//        val firebaseUser = auth.currentUser
//
//        if (firebaseUser == null) {
//            _state.value = AuthState.Error("Пользователь не авторизован")
//            return
//        }
//
//        viewModelScope.launch {
//            try {
//                val token = firebaseUser.getIdToken(true).await().token
//                val email = auth.currentUser!!.email
//                val user = getUserUseCase.invoke(token = token, email = email)
//                _state.value = AuthState.Authenticated
//
//            } catch (e: Exception) {
//                _state.value = AuthState.Error(e.localizedMessage)
//            }
//        }
//    }
//
//    fun signUp(email: String, password: String) {
//        viewModelScope.launch {
//            try {
//
//                val result = auth.createUserWithEmailAndPassword(email, password).await()
//
//
//                val firebaseUser = result.user
//                if (firebaseUser == null) {
//                    _state.value = AuthState.Error("Firebase не вернул пользователя")
//                    return@launch
//                }
//
//                val user = addUserUseCase.addUser(email)
//                _state.value = AuthState.Authenticated
//
//
//            } catch (e: Exception) {
//                _state.value = AuthState.Error(e.localizedMessage)
//            }
//        }
//    }
}
