package com.example.kotlinandroidapp1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinandroidapp1.data.UserPreferences
import com.example.kotlinandroidapp1.layout.MainLayout
import com.example.kotlinandroidapp1.ui.login_screen.LoginScreen
import com.example.kotlinandroidapp1.ui.onboarding_screen.OnboardingScreen
import com.example.kotlinandroidapp1.ui.profile_screen.ProfileDetailPage
import com.example.kotlinandroidapp1.ui.splash_screen.SplashScreen
import com.example.kotlinandroidapp1.ui.term_policy_screen.TermPolicyScreen

@Composable
fun AppNavHost(navController: NavHostController, userPreferences: UserPreferences) {
    val termandpolicyCompletedFlow = userPreferences.onboardingCompleted.collectAsState(initial = null)

    when (val termandpolicyCompletedFlow = termandpolicyCompletedFlow.value) {
        null -> {
            SplashScreen(
                userPreferences = userPreferences,
                onFinished = { completed ->
                    if (completed) {
                        navController.navigate("login_screen") {
                            popUpTo(0)
                        }
                    } else {
                        navController.navigate("term_and_policy") {
                            popUpTo(0)
                        }
                    }
                }
            )
        }
        else -> {
            val startDestination = if (termandpolicyCompletedFlow) {
                "login_screen"
            } else {
                "term_and_policy"
            }
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable("term_and_policy") {
                    TermPolicyScreen(
                        onBoardingClick = { navController.navigate("onboarding") },
                        userPreferences = userPreferences
                    )
                }
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
