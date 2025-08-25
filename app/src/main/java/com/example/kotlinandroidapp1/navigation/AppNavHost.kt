package com.example.kotlinandroidapp1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinandroidapp1.data.UserPreferences
import com.example.kotlinandroidapp1.layout.MainLayout
import com.example.kotlinandroidapp1.ui.home.HomeScreen
import com.example.kotlinandroidapp1.ui.login.LoginScreen
import com.example.kotlinandroidapp1.ui.onboarding.OnboardingScreen
import com.example.kotlinandroidapp1.ui.profile.ProfileDetailPage
import com.example.kotlinandroidapp1.ui.splash_screen.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController, userPreferences: UserPreferences) {
    val onboardingCompletedFlow = userPreferences.onboardingCompleted.collectAsState(initial = null)

    when (val onboardingCompleted = onboardingCompletedFlow.value) {
        null -> {
            SplashScreen()
        }
        true -> {
            NavHost(
                navController = navController,
                startDestination = "login_screen"
            ) {
                composable("onboarding") {
                    OnboardingScreen(
                        onLoginClick = { navController.navigate("login_screen") },
                        userPreferences = userPreferences
                    )
                }
                composable("login_screen") {
                    LoginScreen(
                        onMainLayoutClick = { navController.navigate("main_layout") }
                    )
                }
                composable("main_layout") {
                    MainLayout(parentNavController = navController)
                }
                composable("profile_detail_screen") {
                    ProfileDetailPage(onBackClick = { navController.popBackStack() })
                }
            }
        }
        false -> {
            NavHost(
                navController = navController,
                startDestination = "onboarding"
            ) {
                composable("onboarding") {
                    OnboardingScreen(
                        onLoginClick = { navController.navigate("login_screen") },
                        userPreferences = userPreferences
                    )
                }
                composable("login_screen") {
                    LoginScreen(
                        onMainLayoutClick = { navController.navigate("main_layout") }
                    )
                }
                composable("main_layout") {
                    MainLayout(parentNavController = navController)
                }
                composable("profile_detail_screen") {
                    ProfileDetailPage(onBackClick = { navController.popBackStack() })
                }
            }
        }
    }
}
