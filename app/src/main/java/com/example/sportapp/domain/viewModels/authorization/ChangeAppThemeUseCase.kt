//package com.example.sportapp.domain.viewModels.authorization
//
//import com.example.sportapp.CleanArchitexture.data.repositories.UserRepository
//
//class ChangeAppThemeUseCase {
//
//    suspend fun changeAppTheme(userRep: UserRepository, email: String): Boolean {
//        return try {
//            userRep.changeTheme(email)
//        } catch (e: Exception) {
//            false // или throw e если хочешь пробросить
//        }
//    }
//}
