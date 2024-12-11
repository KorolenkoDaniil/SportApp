package com.example.sportapp.API.entities

import android.util.Log

data class MatchDayEntity(
    val name: String,
    val matches: List<MatchItem>
)

fun matchDaysList (matches : List<MatchItem>) : List<MatchDayEntity>{
    val group = matches.groupBy { it.matchDayName }

    Log.d("ttt1", group.toString())

    val a = group.keys.sorted().map { name ->
        MatchDayEntity(name = name, matches = group[name].orEmpty())
    }

    Log.d("ttt1", a.toString())




    return a
}
