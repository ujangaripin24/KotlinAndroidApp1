package com.example.kotlinandroidapp1.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinandroidapp1.ui.dashboard.DashboardPage
import com.example.kotlinandroidapp1.ui.profile.ProfilePage

@Composable
fun MainLayout() {
    val navController = rememberNavController()
    val items = listOf("dashboard", "profile", "product")

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = navController.currentDestination?.route == screen,
                        onClick = {
                            navController.navigate(screen) {
                                popUpTo("dashboard") { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            when (screen) {
                                "dashboard_page" -> Icon(Icons.Default.Home, contentDescription = null)
                                "profile_page" -> Icon(Icons.Default.Person, contentDescription = null)
                            }
                        },
                        label = { Text(screen) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("dashboard_page") { DashboardPage() }
            composable("profile_page") { ProfilePage() }
        }
    }
}