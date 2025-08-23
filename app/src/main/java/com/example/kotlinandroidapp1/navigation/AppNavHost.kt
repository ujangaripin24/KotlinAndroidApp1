package com.example.kotlinandroidapp1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinandroidapp1.layout.MainLayout
import com.example.kotlinandroidapp1.ui.home.HomeScreen
import com.example.kotlinandroidapp1.ui.login.LoginScreen
import com.example.kotlinandroidapp1.ui.onboarding.OnboardingScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen(
                onLoginClick = { navController.navigate("login_screen") },
            )
        }
        composable("home_screen") {
            HomeScreen()
        }
        composable("login_screen") {
            LoginScreen(
                onMainLayoutClick = { navController.navigate("main_layout") }
            )
        }
        composable("main_layout") {
            MainLayout()
        }
    }
}