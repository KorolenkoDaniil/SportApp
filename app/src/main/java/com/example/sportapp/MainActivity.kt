
package com.example.sportapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.support2026.presentation.navigation.MainNavigation
import com.example.sportapp.support2026.presentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(

) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Composable", "Composable  mainActivity1")

        setContent {

            AppTheme {
                Scaffold(Modifier.fillMaxSize()) { innerPadding ->
                    HomeContent (modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun HomeContent(modifier: Modifier){

    val navController = rememberNavController()

    MainNavigation(
        modifier = modifier,
        navHostController = navController
    )
}


















//package com.example.sportapp
//
//import AppActivityViewModel
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.viewModels
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.rememberNavController
//import com.example.sportapp.support2026.presentation.navigation.MainNavigation
//import com.example.sportapp.support2026.presentation.ui.theme.AppTheme
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class MainActivity : ComponentActivity(
//
//) {
//
//    private val appActivity: AppActivityViewModel by viewModels()
////    private val videoViewModel: YoutubeActivityViewModel by viewModels()
////    private val newsViewModel: NewsActivityViewModel by viewModels()
////    private val matchesViewModel: MatchesActivitySoccerViewModel by viewModels()
////    private val authViewModel: AuthViewModel by viewModels()
////    private val aiViewModel: AIAnswerViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        Log.d("Composable", "Composable  mainActivity1")
//
//        setContent {
//
////            val videoState by videoViewModel.getState().collectAsState()
////            val newsState by newsViewModel.getState().collectAsState()
////            val matchesState by matchesViewModel.getState().collectAsState()
////            val authState by authViewModel.authState.collectAsState()
//
//            AppTheme(
////                authViewModel = authViewModel
//            ) {
//                val navController = rememberNavController()
////                val url = intent.data
////                val startDestination = if (url != null && url.scheme == "korsport" && url.host == "news") {
////                    val newsId = url.lastPathSegment ?: ""
////                    "news/$newsId"
////                } else {
////                    Screen.FirstPage.route
////                }
//
//
////                val startDestination = Screen.LoadingPage.route
//
//                MainNavigation(
//                    modifier = Modifier,
//                    navHostController = navController
//                )
////                MyAppNavigation(
////                    viewModels = viewModels,
//////                    states = states,
////                    navController = navController,
////                    startDestination = startDestination,
////                )
//            }
//        }
//
//    }
//}
//
