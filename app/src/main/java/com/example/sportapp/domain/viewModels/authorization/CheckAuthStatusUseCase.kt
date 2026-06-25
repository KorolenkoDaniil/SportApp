//package com.example.sportapp.domain.viewModels.authorization
//
//import com.example.sportapp.CleanArchitexture.data.repositories.UserRepository
//import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
//import com.example.sportapp.domain.viewModels.authorization.utils.AuthorizationUtils
//import com.google.firebase.auth.FirebaseAuth
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.tasks.await
//
//class CheckAuthStatusUseCase {
//
//    suspend fun checkAuthStatus(
//        _authState: MutableStateFlow<AuthState>,
//        auth: FirebaseAuth,
//        _authorizationUtils: AuthorizationUtils,
//        userRep: UserRepository,
//        _currentUser: MutableStateFlow<UserEntity?>,
//        _themeIsWhite: MutableStateFlow<Boolean>
//    ) {
//        _authState.value = try {
//            if (auth.currentUser == null) {
//                AuthState.Unauthenticated
//            } else {
//                auth.currentUser?.reload()?.await()
//                if (auth.currentUser != null) {
//                    _authorizationUtils.updateCurrentUser(
//                        userRep.getUser(auth.currentUser!!.email!!),
//                        _currentUser,
//                        _themeIsWhite
//
//                    )
//                    AuthState.Authenticated
//                } else {
//                    AuthState.Unauthenticated
//                }
//            }
//        } catch (e: Exception) {
//            AuthState.Error(e.message ?: "Failed to check auth status")
//        }
//    }
//}