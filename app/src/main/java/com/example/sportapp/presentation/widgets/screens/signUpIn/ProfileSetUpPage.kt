package com.example.sportapp.presentation.widgets.screens.signUpIn

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.sportapp.models.viewModels.AuthViewModel
import timber.log.Timber
import java.io.File

@Composable
fun ProfileSetUpPage(viewModel: AuthViewModel, navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ChoosePictureFromGalleryButton(
            modifier = Modifier,
            onPictureChosen = { uri ->
                val file = uriToFile(context, uri)
                viewModel.sendUserImage(file)
            }
        )
//        navController.navigate(Screen.ProfileSetUpPage.route)
    }
}

@Composable
fun ChoosePictureFromGalleryButton(
    modifier: Modifier = Modifier,
    onPictureChosen: (Uri) -> Unit,
) {
    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            Timber.d("Assign uri to image")
            result.uriContent?.let(onPictureChosen)
        } else {
            Timber.e("Image picking failed: ${result.error}")
        }
    }

    Button(
        modifier = modifier.padding(16.dp),
        onClick = {
            imageCropLauncher.launch(buildImagePickerOptions())
        }
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