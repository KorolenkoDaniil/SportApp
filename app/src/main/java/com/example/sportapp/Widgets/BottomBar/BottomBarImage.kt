package com.example.sportapp.widgets.bottomBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BottomBarImage(
    currentPage: MutableState<String>,
    newPage: String,
    pictureId: Int
) {

    Image(
        painter = painterResource(id = pictureId),
        contentDescription = "$newPage Icon",
        modifier = Modifier
            .size(40.dp)
            .clickable (
                indication = null,
                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
            ) {
                currentPage.value = newPage
            }
            .then(
                if (currentPage.value == newPage) {
                    Modifier.alpha(1.0f)
                } else {
                    Modifier.alpha(0.5f)
                }
            )
    )
}