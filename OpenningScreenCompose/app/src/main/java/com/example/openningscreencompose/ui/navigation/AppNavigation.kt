package com.example.openningscreencompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.openningscreencompose.ui.screens.auth.welcome.WelcomeScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.openningscreencompose.ui.screens.auth.login.LoginScreen
import com.example.openningscreencompose.ui.screens.auth.register.RegisterScreen
import com.example.openningscreencompose.ui.screens.home_user.book.BookScreen

@Composable
fun AppNavigation() {
    // 1. Tạo một bộ điều khiển (NavController) để ghi nhớ lịch sử chuyển trang
    val navController = rememberNavController()

    // 2. Tạo bản đồ điều hướng (NavHost), thiết lập màn hình bắt đầu là Welcome
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(
                onNavigationToLogin = {
                    navController.navigate(Screen.Login.route)
                },

                onNavigationToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(route = Screen.Login.route) {
            LoginScreen(
                onNavigationToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onNavigationUserHome = {
                    navController.navigate(Screen.Book.route)
                }
            )
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(
                onNavigationToLogin = {
                    navController.navigate(Screen.Login.route)
                },
                onNavigationRegisterSendEmail = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(route = Screen.Book.route) {
            BookScreen(

            )
        }
    }
}