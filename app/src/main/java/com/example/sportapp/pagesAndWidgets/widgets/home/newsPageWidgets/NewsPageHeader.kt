package com.example.sportapp.pagesAndWidgets.widgets.home.newsPageWidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.News_title_style

@Composable
fun NewsPageHeader(title: String, navController: NavController) {

    Column {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(R.drawable.arrow_small_left_1),
                contentDescription = "",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(text = "\t\t\t$title", style = News_title_style)
    }
}
