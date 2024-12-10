import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.API.entities.MatchesActivityViewModel
import com.example.sportapp.R
import com.example.sportapp.widgets.BottomBar.BottomBarImage

@Composable
fun BottomBar(currentPage: MutableState<String>) {

    BottomAppBar(
        modifier = Modifier.fillMaxWidth()
            .height(100.dp),
        containerColor = Color(0xFFFFFFFF)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBarImage(currentPage, "home", R.drawable.home)
            BottomBarImage(currentPage, "matches", R.drawable.matches)
            BottomBarImage(currentPage, "video", R.drawable.videos)
            BottomBarImage(currentPage, "like", R.drawable.like)
        }
    }
}
