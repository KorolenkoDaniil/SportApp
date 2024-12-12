package com.example.sportapp.widgets.home

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.sportapp.shared.ItemCard

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