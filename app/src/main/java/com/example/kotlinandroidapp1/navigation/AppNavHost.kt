package com.example.kotlinandroidapp1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinandroidapp1.ui.home.HomeScreen
import com.example.kotlinandroidapp1.ui.onboarding.OnboardingScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen (
                onContinue = {navController.navigate("home")}
            )
        }
        composable("home") {
            HomeScreen()
        }
    }
}