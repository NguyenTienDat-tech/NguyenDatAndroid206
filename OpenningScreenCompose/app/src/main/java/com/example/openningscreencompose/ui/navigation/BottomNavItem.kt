package com.example.openningscreencompose.ui.navigation


import com.example.openningscreencompose.R
import kotlin.reflect.KClass

// Data class đại diện cho 1 nút trên thanh điều hướng
data class BottomNavItem<T: Any> (
    val title: String,
    val icon: Int,
    val routeClass: KClass<T>, // Dùng cho hàm hasRoute()
    val routeObject: T // Dùng cho hàm navigate()
)


val bottomNavItems = listOf(
    BottomNavItem(
        title = "Đặt lịch",
        icon = R.drawable.calendar,
        routeClass = RoutesAndEvent.BookRouter::class,
        routeObject = RoutesAndEvent.BookRouter
    ),
    BottomNavItem(
        title = "Tư vấn AI",
        icon = R.drawable.ai,
        routeClass = RoutesAndEvent.ChatBotRouter::class,
        routeObject = RoutesAndEvent.ChatBotRouter
    ),
    BottomNavItem(
        title = "Locket",
        icon = R.drawable.locket,
        routeClass = RoutesAndEvent.LocketRouter::class,
        routeObject = RoutesAndEvent.LocketRouter
    ),
    BottomNavItem(
        title = "Cài đặt",
        icon = R.drawable.profile,
        routeClass = RoutesAndEvent.ProfileRouter::class,
        routeObject = RoutesAndEvent.ProfileRouter
    ),
)