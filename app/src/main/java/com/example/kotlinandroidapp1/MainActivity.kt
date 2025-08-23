package com.example.kotlinandroidapp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.kotlinandroidapp1.navigation.AppNavHost
import com.example.kotlinandroidapp1.ui.theme.KotlinAndroidApp1Theme
import com.mapbox.common.MapboxOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // token mapbox
        val token = getString(R.string.mapbox_access_token)
        MapboxOptions.accessToken = token
        setContent {
            KotlinAndroidApp1Theme {
                Surface (color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}
