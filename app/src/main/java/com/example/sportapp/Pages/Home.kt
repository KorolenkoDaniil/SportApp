package com.example.sportapp.pages

import AppActivityViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.viewModels.MatchesActivityViewModel
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.ui.theme.style1
import com.example.sportapp.widgets.home.CurrentMatch
import com.example.sportapp.widgets.home.NewsCards


@Composable
fun HomePage(
    state: MatchesState,
    mainViewModel: MatchesActivityViewModel,
    appActivity: AppActivityViewModel
) {
    appActivity.changePageName("Home")
    when (state) {

        is MatchesState.MatchesContent -> {
            LazyColumn {
                item { CurrentMatch(mainViewModel.nearestMatch) }
                item { Spacer(modifier = Modifier.height(32.dp)) }
                item { Text(text = "Sport news", style = style1) }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item { NewsCards() }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item { Text(text = "Highlights", style = style1) }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item { NewsCards() }
                item { Spacer(modifier = Modifier.height(24.dp)) }
            }
        }

        //ошибька загрузки матчей
        is MatchesState.Error -> {
            CommonError(mainViewModel)
        }

        //загрузка матчей
        MatchesState.Load -> {
            Loading()
        }
    }
}
