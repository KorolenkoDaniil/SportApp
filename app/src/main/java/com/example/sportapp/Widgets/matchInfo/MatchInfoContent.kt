import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.domain.MatchEntity
import com.example.sportapp.widgets.matchInfo.MatchCardContent

@Composable
fun MatchInfoContent(matchDay: MatchEntity?) {
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

    val imageSize by animateDpAsState(
        targetValue = currentSize,
        animationSpec = if (currentSize == 10.dp) {
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
                        .background(color = Color.White)
                        .fillMaxWidth()
                ) {
                    MatchCardContent(matchDay,  imageSize)
                }
            }
        }
        items(100) { index ->
            Text(
                text = "Item $index",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}
