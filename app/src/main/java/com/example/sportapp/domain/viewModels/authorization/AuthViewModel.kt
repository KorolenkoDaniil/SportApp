package com.example.sportapp.domain.viewModels.authorization


import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.sportapp.CleanArchitexture.data.repositories.UserRepository
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.domain.viewModels.authorization.utils.AuthorizationUtils
import com.example.sportapp.presentation.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File


class AuthViewModel(
    private val checkAuthStatusUseCase: CheckAuthStatusUseCase = CheckAuthStatusUseCase(),
    private val authorizationUtils: AuthorizationUtils = AuthorizationUtils(),
    private val userRep: UserRepository = UserRepository(),
    private val loginUseCase: LoginUseCase = LoginUseCase(),
    private val signUpUseCase: SignUpUseCase = SignUpUseCase(),
    private val sendUserImageUseCase: SendUserImageUseCase = SendUserImageUseCase(),
) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    private val _imageState = MutableStateFlow<ImageSubmissionState>(ImageSubmissionState.Initial)
    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    private val _currentUserPhotoFile = MutableStateFlow<File?>(null)

    var currentUserPhotoFile: StateFlow<File?> = _currentUserPhotoFile
    val authState: StateFlow<AuthState> = _authState
    val imageState: StateFlow<ImageSubmissionState> = _imageState
    val currentUser: StateFlow<UserEntity?> = _currentUser
    val email = mutableStateOf("")
    val password = mutableStateOf("")



    fun setUserPhotoFile(file: File) {
        _currentUserPhotoFile.value = file
    }

    init {
        checkAuthStatus()
    }





    fun login(email: String, password: String) {

        if (authorizationUtils.checkEmailAndPassword(
                email = email,
                password = password,
                _authState = _authState
            )
        ) {
            _authState.value = AuthState.Loading
            viewModelScope.launch {
                loginUseCase.login(
                    _authState = _authState,
                    auth = auth,
                    email = email,
                    password = password,
                    authorizationUtils = authorizationUtils,
                    userRep = userRep,
                    _currentUser = _currentUser
                )
            }
        } else return
    }


    fun signup(email: String, password: String, context: Context) {
        if (authorizationUtils.checkEmailAndPassword(
                email = email,
                password = password,
                _authState = _authState
            )
        ) {

            _authState.value = AuthState.Loading
            viewModelScope.launch {
                signUpUseCase.signUp(
                    _authState = _authState,
                    auth = auth,
                    email = email,
                    password = password,
                    authorizationUtils = authorizationUtils,
                    userRep = userRep,
                    _currentUser = _currentUser,
                    context = context
                )
            }
        } else return
    }


    fun deleteUser(navController: NavHostController) {
        viewModelScope.launch {
            _authState.value = try {
                auth.currentUser?.delete()?.await()
                signOut(navController)
                AuthState.Unauthenticated
            } catch (e: Exception) {
                AuthState.Error(e.message ?: "Failed to delete user")
            }
        }
    }

    fun signOut(navController: NavHostController) {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
        navController.navigate(Screen.LoginPage.route)
        authorizationUtils.changeImageState(ImageSubmissionState.Initial, _imageState)
    }

    fun changeImageState(state: ImageSubmissionState) {
        authorizationUtils.changeImageState(
            state = state,
            _imageState = _imageState
        )
    }

    fun sendUserImage(image: File, email: String) {
        viewModelScope.launch {
            sendUserImageUseCase.sendUserImage(
                email = email,
                authorizationUtils = authorizationUtils,
                userRep = userRep,
                _currentUser = _currentUser,
                _imageState =  _imageState,
                image = image
            )
        }
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {

            checkAuthStatusUseCase.checkAuthStatus(
                _authState = _authState,
                auth = auth,
                _authorizationUtils = authorizationUtils,
                userRep = userRep,
                _currentUser = _currentUser
            )
        }
    }



}

sealed class AuthState {
    data object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
    data object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}

sealed class ImageSubmissionState {
    data object Received : ImageSubmissionState()
    data object Initial : ImageSubmissionState()
    data object Loading : ImageSubmissionState()
    data class Error(val message: String) : ImageSubmissionState()
}