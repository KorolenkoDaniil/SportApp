package com.example.sportapp.support2026.presentation.screeens.NewsDetailsPage.widgets

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

//
//@Composable
//fun NewsPageHeader(title: String, navController: NavController, icon_arrow: Int) {
//
//    Column {
//        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            Image(
//                painter = painterResource(icon_arrow),
//                contentDescription = "",
//                modifier = Modifier.clickable {
//                    navController.popBackStack()
//                }
//            )
//        }
//
//        Spacer(Modifier.height(16.dp))
//
//        Text(text = "\t\t\t$title", style = MaterialTheme.typography.News_title_style)
//    }
//}
