package com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.R
import com.example.sportapp.domain.viewModels.authorization.AuthViewModel
import com.example.sportapp.presentation.navigation.Screen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingsHeader (authViewModel: AuthViewModel, navController: NavController){

    val painter = rememberAsyncImagePainter(
        model = authViewModel.currentUserPhotoFile.value
            ?: authViewModel.currentUser.value?.pictureURL
    )


    Row (verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(40.dp))
        ) {
            Image(
                painter = painter,
                contentDescription = "User profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        navController.navigate(Screen.ProfileSetUpPage.route)
                    }
            )

            Box (
                Modifier.size(32.dp).align(Alignment.BottomEnd)
            ) {
                Image(
                    painter = painterResource(R.drawable.edit_icon),
                    contentDescription = "User profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.TopStart)
                )
            }
        }

        Spacer(Modifier.width(20.dp))

        Text(text = authViewModel.currentUser.value!!.email, maxLines = 1)

    }
}