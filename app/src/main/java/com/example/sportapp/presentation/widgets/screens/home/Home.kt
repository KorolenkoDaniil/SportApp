//package com.example.sportapp.presentation.widgets.screens.home
//
////import com.example.sportapp.domain.viewModels.news.NewsState
//import android.util.Log
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.unit.Dp
//import androidx.navigation.NavHostController
//import com.example.sportapp.presentation.navigation.Screen
//import com.example.sportapp.presentation.widgets.common.shared.CommonError
//import com.example.sportapp.presentation.widgets.common.shared.Loading
//import com.example.sportapp.presentation.widgets.screens.home.home.HomePageContent
//import com.example.sportapp.support2026.features.news.presentation.NewsState
//import com.example.sportapp.support2026.features.news.presentation.NewsViewModel
//
//
//@Composable
//fun HomePage (
////    viewModels: ViewModelContainer,
////    states: StatesContainer,
//    navController: NavHostController,
//    horizontalPaddings: Dp,
//    newsViewModel: NewsViewModel
//) {
//
////    val newsState by viewModels.newsViewModel.state.collectAsState()
//    val newsState by newsViewModel.state.collectAsState()
//
//
//    Log.d("Composable", "Composable  HomePage")
//    Log.d("Composable", newsState.toString())
//
//    HomePageContent(
//        navController = navController,
//        horizontalPaddings = horizontalPaddings,
//        newsViewModel = newsViewModel
//    )
//
//    if (newsState is NewsState.Load) {
//        Loading()
//    }
//
//    // Обработка ошибки
//    if (newsState is NewsState.Error) {
//        CommonError(
////            newsViewModel, // Передаем актуальную hilt модель вместо viewModels.newsViewModel
//            Screen.Home.route,
//            navController,
//            "новости"
//        )
//    }
//}
//
//
////        is MatchesState.Error -> {
////            CommonError(viewModels.matchesViewModel, Screen.Home.route, navController, "матчи")
////        }
////
////        MatchesState.Load -> {
////            Loading()
////        }
////    }
////}
//
