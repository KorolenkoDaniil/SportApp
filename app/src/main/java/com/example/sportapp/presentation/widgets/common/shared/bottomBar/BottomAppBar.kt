
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sportapp.R
import com.example.sportapp.presentation.widgets.common.shared.bottomBar.BottomBarImage
import com.example.sportapp.shared.modifierExtensions.dropShadow

@Composable
fun BottomNavBar(
    navController: NavController,
    appActivity: AppActivityViewModel,
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .dropShadow(),
        containerColor = Color(0xFFFFFFFF)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                BottomBarImage("home", R.drawable.home, navController, appActivity)
                BottomBarImage("matches", R.drawable.matches, navController, appActivity)
                BottomBarImage("video", R.drawable.videos, navController, appActivity)
                BottomBarImage("like", R.drawable.ai, navController, appActivity)
            }
        }
    }
}
