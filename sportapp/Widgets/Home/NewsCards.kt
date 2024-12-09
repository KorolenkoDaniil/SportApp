package com.example.sportapp.Widgets.Home

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.sportapp.Shared.ItemCard

@Composable

fun NewsCards (){
    LazyRow {
        itemsIndexed(
            listOf("sadf", "asdfsfd", "asdfsfd", "asdfsfd asdfsfdggg asdfsfd asdfsfd asdfsfd asdfsfd asdfsfd asdfsfd ", "asdfsfd",)
        ) {
          index, item,  ->
            ItemCard(item)
        }
    }
}