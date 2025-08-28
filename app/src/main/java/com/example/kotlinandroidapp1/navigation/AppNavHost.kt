package com.example.kotlinandroidapp1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinandroidapp1.data.local.UserPreferences
import com.example.kotlinandroidapp1.layout.MainLayout
import com.example.kotlinandroidapp1.ui.login_screen.LoginScreen
import com.example.kotlinandroidapp1.ui.onboarding_screen.OnboardingScreen
import com.example.kotlinandroidapp1.ui.profile_screen.ProfileDetailPage
import com.example.kotlinandroidapp1.ui.splash_screen.SplashScreen
import com.example.kotlinandroidapp1.ui.term_policy_screen.TermPolicyScreen

@Composable
fun AppNavHost(navController: NavHostController, userPreferences: UserPreferences) {
    val splashDone = remember { mutableStateOf(false) }
    val startDestination = remember { mutableStateOf("login_screen") }

    if (!splashDone.value) {
        SplashScreen(
            userPreferences = userPreferences,
            onFinished = { token, onboardingCompleted ->
                startDestination.value = when {
                    !onboardingCompleted -> "term_and_policy"
                    token.isNullOrEmpty() -> "login_screen"
                    else -> "main_layout"
                }
                splashDone.value = true
            }
        )
    } else {
            NavHost(
                navController = navController,
                startDestination = startDestination.value
            ) {
                composable("term_and_policy") {
                    TermPolicyScreen(
                        onBoardingClick = { navController.navigate("onboarding") },
                        userPreferences = userPreferences
                    )
                }
                composable("onboarding") {
                    OnboardingScreen(
                        onLoginClick = { navController.navigate("login_screen") {
                            popUpTo("onboarding") {inclusive = true}
                        }},
                        userPreferences = userPreferences
                    )
                }
                composable("login_screen") {
                    LoginScreen(
                        onMainLayoutClick = {
                            navController.navigate("main_layout") {
                                popUpTo("login_screen") { inclusive = true }
                                launchSingleTop = true
                            }
                        }
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
