
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sportapp.R
import com.example.sportapp.widgets.bottomBar.BottomBarImage

@Composable
fun BottomNavBar(
    navController: NavController
) {

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        containerColor = Color(0xFFFFFFFF)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {

            BottomBarImage("home", R.drawable.home, navController)
            BottomBarImage("matches", R.drawable.matches, navController)
            BottomBarImage("video", R.drawable.videos, navController)
            BottomBarImage("like", R.drawable.like, navController)
        }
    }
}
