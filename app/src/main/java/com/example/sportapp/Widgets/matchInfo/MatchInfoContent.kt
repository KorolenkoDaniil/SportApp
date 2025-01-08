
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.domain.EventEntity
import com.example.sportapp.domain.MatchEntity
import com.example.sportapp.ui.theme.Blue100
import com.example.sportapp.widgets.home.CurrentMatch
import com.example.sportapp.widgets.matchInfo.MatchCardContent

@Composable
fun MatchInfoContent(eventsList: List<EventEntity>, match: MatchEntity) {

    val lazyListState = rememberLazyListState()

    val isAtTop = remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex == 0 && lazyListState.firstVisibleItemScrollOffset == 0 }
    }

    var currentHeight by remember { mutableStateOf(200.dp) }
    var currentSize by remember { mutableStateOf(100.dp) }

    LaunchedEffect(isAtTop.value) {
        currentHeight = if (isAtTop.value) 150.dp else 70.dp
        currentSize = if (isAtTop.value) 100.dp else 10.dp
    }

    val boxHeight by animateDpAsState(
        targetValue = currentHeight,
        animationSpec = if (currentHeight == 70.dp) {
            tween(durationMillis = 4000)
        } else {
            tween(durationMillis = 4000)
        }
    )


    LazyColumn(state = lazyListState) {
        item {
            Box(
                modifier = Modifier
                    .height(boxHeight)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.Transparent)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .height(boxHeight)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Blue100)
                        .fillMaxWidth()
                ) {
                    CurrentMatch(match)
                }
            }
        }
        items(eventsList.size) { index ->
            MatchCardContent(eventsList[index])
        }
    }
}
