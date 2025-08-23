package com.example.kotlinandroidapp1.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinandroidapp1.ui.dashboard.DashboardPage
import com.example.kotlinandroidapp1.ui.profile.ProfilePage

@Composable
fun MainLayout() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavScreen.Dashboard,
        BottomNavScreen.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(BottomNavScreen.Dashboard.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavScreen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavScreen.Dashboard.route) { DashboardPage() }
            composable(BottomNavScreen.Profile.route) { ProfilePage() }
        }
    }
}

sealed class BottomNavScreen(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : BottomNavScreen("dashboard_page", "Dashboard", Icons.Default.Home)
    object Profile : BottomNavScreen("profile_page", "Profile", Icons.Default.Person)
}
