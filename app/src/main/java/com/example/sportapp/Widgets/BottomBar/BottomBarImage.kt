package com.example.sportapp.widgets.bottomBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBarImage(
    newPage: String,
    pictureId: Int,
    navController: NavController,
) {
    val isSelected: Boolean
    val currentRoute by navController.currentBackStackEntryAsState()

    if ((currentRoute?.destination?.route?.contains("news") == true) && (newPage == "home")){
        isSelected = true
    }
    else {
        isSelected = currentRoute?.destination?.route == newPage
    }


    Image(
        painter = painterResource(id = pictureId),
        contentDescription = "$newPage Icon",
        modifier = Modifier
            .size(40.dp)
            .clickable(
                indication = null,
                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
            ) {
                navController.navigate(newPage)
            }
            .alpha(if (isSelected) 1.0f else 0.5f)
    )
}
