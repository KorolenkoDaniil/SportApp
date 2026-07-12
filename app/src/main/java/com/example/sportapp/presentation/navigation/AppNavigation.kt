//package com.example.sportapp.presentation.navigation
//
//import BottomNavBar
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
////import com.example.sportapp.containers.StatesContainer
//import com.example.sportapp.containers.ViewModelContainer
////import com.example.sportapp.presentation.widgets.common.shared.LoadingPage
//import com.example.sportapp.presentation.widgets.screens.home.HomePage
//
//@Composable
//fun MyAppNavigation(
//    viewModels: ViewModelContainer,
////    states: StatesContainer,
//    navController: NavHostController,
//
//    startDestination: String,
//) {
//
//    val showBar = remember { mutableStateOf(false)}
//
//    Log.d("Composable", "Composable  MyAppNavigation")
//
//
//    Scaffold(
//        containerColor = Color(0xFFEBEFF4),
//
//        bottomBar = {
//            if (showBar.value) {
//                BottomNavBar(navController = navController, viewModels.appActivity)
//            }
//        },
//
//
//
//        ) { innerPadding ->
//
//
//        val topPaddings = if (showBar.value) 14.dp else 0.dp
//        val horizontalPaddings = 12.dp
//
//        NavHost(
//            navController = navController,
//            startDestination = startDestination,
//            modifier = Modifier
//                .padding(innerPadding)
//                .background(MaterialTheme.colorScheme.background)
//                .padding( top = topPaddings),
//
//            builder = {
//                composable(Screen.LoginPage.route) {
//                    showBar.value = false
//                    LoginPage(navController, viewModels.authViewModel, viewModels.appActivity)
//                }
//                composable(Screen.SignupPage.route) {
//                    showBar.value = false
//                    SignupPage(navController, viewModels.authViewModel, viewModels.appActivity)
//                }
//                composable(Screen.Loading.route) {
//                    Loading()
//                }
//
//                composable(Screen.LoadingPage.route) {
//                    showBar.value = true
//                    LoadingPage(
//                        viewModels = viewModels,
//                        navController = navController
//                    )
//                }
//                composable(Screen.Home.route) {
//                    showBar.value = true
//                    HomePage(
//                        viewModels = viewModels,
////                        states = states,
//                        navController = navController,
//                        horizontalPaddings = horizontalPaddings,
//                    )
//                }
//
//                composable(Screen.FirstPage.route) {
//                    FirstPage(navController, states.authState)
//                }
//                composable(Screen.Matches.route) {
//
//                    MatchesPage(
//                        viewModels.matchesViewModel, states.matchesState, viewModels.appActivity, navController, horizontalPaddings)
//                }
//                composable(Screen.VideoListPage.route) {
//                    VideoListPage(
//                        videoViewModel = viewModels.videoViewModel,
//                        navController = navController
//                    )
//                }
//
//                composable(Screen.Like.route) {
//                    AIChatPage(
//                        viewModels.authViewModel, navController, viewModels, horizontalPaddings
//                    )
//                }
//
//                composable(Screen.News.route) { backStackEntry ->
//
//                    val newsDateTime = backStackEntry.arguments?.getString("newsId")
//
//                    val parsedDateTime = LocalDateTime.parse(newsDateTime!!)
//
//                    NewsPage(
//                        viewModels,
//                        states,
//                        parsedDateTime.toString(),
//                        navController,
//                        horizontalPaddings,
//                        showBar,
//
//                    )
//                }
//
//                composable (Screen.VideoPlayerPage.route) {
//                    VideoPlayerPage(viewModels.videoViewModel)
//                }
//
//                composable (Screen.ProfileSetUpPage.route) {
//                    ProfileSetUpPage(
//                        authViewModel = viewModels.authViewModel,
//                        navController
//                    )
//                }
//
//                composable (Screen.SettingsPage.route) {
//                    SettingsPage(
//                        authViewModel = viewModels.authViewModel,
//                        navController,
//                        navController,
//                        topPaddings,
//                    )
////                }
//            }
//        )
//    }
//}
//
