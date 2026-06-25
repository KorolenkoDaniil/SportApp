package com.example.sportapp.presentation.widgets.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.models.viewModels.NewsState
import com.example.sportapp.presentation.navigation.Screen
import com.example.sportapp.presentation.widgets.common.shared.CommonError
import com.example.sportapp.presentation.widgets.common.shared.Loading
import com.example.sportapp.presentation.widgets.screens.home.home.HomePageContent


@Composable
fun HomePage(
    viewModels: ViewModelContainer,
//    states: StatesContainer,
    navController: NavHostController,
    horizontalPaddings: Dp,
) {
//    when (states.matchesState) {
//
//        is MatchesState.MatchesContent -> {

    val newsState by viewModels.newsViewModel.state.collectAsState()


    Log.d("Composable", "Composable  HomePage")
            Log.d("Composable", newsState.toString())

    when (newsState) {

                is NewsState.NewsContent -> {

                    HomePageContent(
                        viewModels,
                        navController = navController,
                        horizontalPaddings,
                    )
                }

                is NewsState.Error -> {
                    Log.d("tttNews", "ошибка Home")
                    CommonError(
                        viewModels.newsViewModel,
                        Screen.Home.route,
                        navController,
                        "новости"
                    )
                }

                is NewsState.Load -> {
                    Loading()
                }
            }
        }

//        is MatchesState.Error -> {
//            CommonError(viewModels.matchesViewModel, Screen.Home.route, navController, "матчи")
//        }
//
//        MatchesState.Load -> {
//            Loading()
//        }
//    }
//}

