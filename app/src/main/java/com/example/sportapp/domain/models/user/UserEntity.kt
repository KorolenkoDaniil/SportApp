package com.example.sportapp.CleanArchitexture.domain.models.user

data class UserEntity (
    val email: String,
    var pictureURL: String,
    private val _isWhiteTheme: Boolean
){
    fun getIsWhiteTheme() : Boolean{
        return _isWhiteTheme
    }
}