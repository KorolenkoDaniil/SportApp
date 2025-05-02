package com.example.sportapp.presentation.widgets.screens.signUpIn

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.ImageSubmissionState
import com.example.sportapp.presentation.navigation.Screen
import timber.log.Timber
import java.io.File

@Composable
fun ProfileSetUpPage(authViewModel: AuthViewModel, navController: NavController) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ChoosePictureFromGalleryButton(
            modifier = Modifier,
            navController,
            authViewModel,
            context,
            onPictureChosen = { uri ->
                val file = uriToFile(context, uri)
                authViewModel.sendUserImage(file, authViewModel.email.value)
            }
        )

    }
}

@Composable
fun ChoosePictureFromGalleryButton(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    context: Context,
    onPictureChosen: (Uri) -> Unit,
) {
    val imageState by authViewModel.imageState.collectAsState()

    val buttonEnabled = remember { mutableStateOf(true) }

    LaunchedEffect(imageState) {
        when (imageState) {
            is ImageSubmissionState.Received -> {
                navController.navigate(Screen.Home.route)
                buttonEnabled.value = true
            }

            is ImageSubmissionState.Error -> {
                Toast.makeText(context, "Ошибка загрузки изображения. Попробуйте снова.", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.LoginPage.route)
                authViewModel.changeImageState(ImageSubmissionState.Initial)
                buttonEnabled.value = false
            }

            is ImageSubmissionState.Loading -> {
                Toast.makeText(context, "Загрузка изображения...", Toast.LENGTH_SHORT).show()
                buttonEnabled.value = false
            }

            is ImageSubmissionState.Initial -> {
                Toast.makeText(context, "Сначала выберите изображение", Toast.LENGTH_SHORT).show()
                buttonEnabled.value = true
            }
        }
    }

    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            Timber.d("Assign uri to image")
            result.uriContent?.let(onPictureChosen)
        } else {
            Timber.e("Image picking failed: ${result.error}")
            Toast.makeText(context, "Ошибка при выборе изображения", Toast.LENGTH_SHORT).show()
        }
    }

    Button(
        modifier = modifier.padding(16.dp),
        onClick = {
            imageCropLauncher.launch(buildImagePickerOptions())
        },
        enabled = buttonEnabled.value

    ) {
        Text(text = "Выбрать фото")
    }

}



fun buildImagePickerOptions() = options {
    setGuidelines(CropImageView.Guidelines.ON)
    setCropShape(CropImageView.CropShape.RECTANGLE)
    setCropMenuCropButtonTitle("Готово")
    setAspectRatio(1, 1)
    setImageSource(includeCamera = true, includeGallery = true)
}


fun uriToFile(context: Context, uri: Uri): File {
    val inputStream = context.contentResolver.openInputStream(uri)

    val file = File.createTempFile("upload_", ".jpg", context.cacheDir)

    inputStream.use { input ->
        file.outputStream().use { output ->
            input?.copyTo(output)
        }
    }

    return file
}