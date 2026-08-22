package com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.support2026.presentation.ui.IconsSize_20
import com.example.sportapp.support2026.presentation.ui.IconsSize_24

@Composable
fun TopBar (navController: NavHostController) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "",
            modifier = Modifier
                .size(IconsSize_24)
                .clickable {
                navController.popBackStack()
            }
        )

        Image(
                painter = painterResource(id = R.drawable.ic_share),
                contentDescription = "",
                modifier = Modifier
                    .size(IconsSize_20)
//                modifier = Modifier.clickable {
//                    navController.popBackStack()
//                }
            )

    }
}