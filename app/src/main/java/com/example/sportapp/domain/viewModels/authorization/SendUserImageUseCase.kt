package com.example.sportapp.domain.viewModels.authorization

import android.util.Log
import com.example.sportapp.CleanArchitexture.data.repositories.UserRepository
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.data.BaseUrl
import com.example.sportapp.domain.viewModels.authorization.utils.AuthorizationUtils
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File

class SendUserImageUseCase {

    suspend fun sendUserImage(
        email: String,
        authorizationUtils: AuthorizationUtils,
        userRep: UserRepository,
        _currentUser: MutableStateFlow<UserEntity?>,
        _imageState: MutableStateFlow<ImageSubmissionState>,
        image: File,
    ) {
        try {
            authorizationUtils.changeImageState(ImageSubmissionState.Loading, _imageState)
            var imageId = authorizationUtils.uploadImageAndGetId(image, email, userRep)
            imageId = imageId.substring(1, imageId.length - 1)
            val newPictureUrl = "$BaseUrl/UserImage/$imageId"

            _currentUser.value = _currentUser.value?.copy(pictureURL = newPictureUrl)

            Log.d("ImageId", "Received Image ID: $newPictureUrl")

            authorizationUtils.changeImageState(ImageSubmissionState.Received, _imageState)
        } catch (e: Exception) {
            Log.d("ImageId", "Error uploading image: ${e.message}")
            authorizationUtils.changeImageState(
                ImageSubmissionState.Error("ошибкеа отправики фотографии"),
                _imageState
            )
        }
    }
}