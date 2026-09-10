package com.example.openningscreencompose.ui.navigation

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome_screen")
    object Login: Screen("login_screen")
    object Register: Screen("register_screen")



    object Book: Screen("book_screen")
}