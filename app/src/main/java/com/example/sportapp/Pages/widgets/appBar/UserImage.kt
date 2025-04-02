package com.example.sportapp.pages.widgets.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.models.viewModels.AuthViewModel


@Composable
fun UserImage(authViewModel: AuthViewModel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(50))
            .background(color = Color.Gray)
            .padding(end = 16.dp),
        onClick = {
            authViewModel.signOut(navController)
        }
    ) {}
}
