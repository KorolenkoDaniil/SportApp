package com.example.sportapp.pagesAndWidgets.widgets.home.newsPageWidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.sportapp.R

@Composable
fun NewsPageHeader (){
    Row () {
        Image(painterResource(R.drawable.arrow_small_left_1), "")
        Image(painterResource(R.drawable.arrow_small_left_1), "")
    }
}