package com.example.sportapp.CleanArchitexture.domain.preferencesManager

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager (context: Context){
    private  val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("KorSportPrefs", Context.MODE_PRIVATE)

    fun saveLimit (messageLimit: Int){
        val editor = sharedPreferences.edit()
        editor.putInt("message_limit", messageLimit)
        editor.apply()
    }

    fun  getData () : Int {
        val defaultValue = 10
        return sharedPreferences.getInt("message_limit", defaultValue)
    }
}
