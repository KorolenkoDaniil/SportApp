package com.example.sportapp.pages.widgets.requests

import android.os.Build
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.messaging.FirebaseMessaging

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationPermissionRequest() {



    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        Log.d("tttPermission", "Разрешение не требуется для этой версии Android.")
        return
    }

    val permissionState = rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
        if (!permissionState.status.isGranted) {
            Log.d("tttPermission", "Запрос разрешения...")
            permissionState.launchPermissionRequest()
        } else {
            Log.d("tttPermission", "Разрешение уже предоставлено.")
            FirebaseMessaging.getInstance().subscribeToTopic("MatchNotification")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("tttTopic", "Успешная подписка на тему!")
                    } else {
                        Log.d("tttTopic","Ошибка при подписке на тему: ${task.exception}")
                    }
                }
        }
    }
}
