package com.example.openningscreencompose.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.openningscreencompose.ui.screens.auth.welcome.WelcomeScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.openningscreencompose.ui.screens.auth.login.LoginScreen
import com.example.openningscreencompose.ui.screens.auth.register.RegisterScreen
import com.example.openningscreencompose.ui.screens.home_user.book.BookScreen
import com.example.openningscreencompose.ui.screens.home_user.chatbot.ChatbotScreen
import com.example.openningscreencompose.ui.screens.home_user.locket.LocketScreen
import com.example.openningscreencompose.ui.screens.home_user.profile.ProfileScreen
import com.example.openningscreencompose.ui.theme.color_primary
import com.example.openningscreencompose.ui.theme.color_text_hint

@Composable
fun AppNavigation() {
    // Tạo một bộ điều khiển (NavController) để ghi nhớ lịch sử chuyển trang
    val navController = rememberNavController()

    // Kiểm tra xem người dùng đang ở màn hình nào
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isAuthScreen = currentDestination?.hasRoute(RoutesAndEvent.WelcomeRouter::class) == true ||
            currentDestination?.hasRoute(RoutesAndEvent.LoginRouter::class) == true ||
            currentDestination?.hasRoute(RoutesAndEvent.RegisterRouter::class) == true

    // Chỉ show BottomBar khi KHÔNG PHẢI là màn hình Auth
    val showBottomBar = !isAuthScreen


    Scaffold(
        // ÉP SCAFFOLD KHÔNG TỰ ĐỘNG CỘNG CHIỀU CAO NÚT HỆ THỐNG VÀO innerPadding
        contentWindowInsets = WindowInsets(0.dp),

        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    // ÉP THANH ĐIỀU HƯỚNG BỎ KHOẢNG TRẮNG THỪA BÊN DƯỚI
                    windowInsets = WindowInsets(0.dp)
                ) {
                    bottomNavItems.forEach { item ->
                        // Kiểm tra xem tab này có đang được chọn không để tô màu
                        val isSelected = currentDestination?.hasRoute(item.routeClass) == true

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                if (!isSelected) {
                                    navController.navigate(item.routeObject) {
                                        // Xóa sạch lịch sử để không bị back lại các tab khác
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = item.title,
                                    tint = if (isSelected) color_primary else color_text_hint
                                )
                            },
                            label = {
                                Text(item.title)
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        // Tạo bản đồ điều hướng (NavHost), thiết lập màn hình bắt đầu là Welcome
        NavHost(
            navController = navController,
            startDestination = RoutesAndEvent.WelcomeRouter,
            modifier = Modifier.padding(innerPadding) // Phải có innerPadding để không bị thanh điều hướng che mất nội dung
        ) {

            //Auth
            composable<RoutesAndEvent.WelcomeRouter> {
                WelcomeScreen(
                    onNavigationToLogin = {
                        navController.navigate(RoutesAndEvent.LoginRouter)
                    },

                    onNavigationToRegister = {
                        navController.navigate(RoutesAndEvent.RegisterRouter)
                    }
                )
            }

            composable<RoutesAndEvent.LoginRouter> {
                LoginScreen(
                    onNavigationToRegister = {
                        navController.navigate(RoutesAndEvent.RegisterRouter)
                    },
                    onNavigationUserHome = {
                        navController.navigate(RoutesAndEvent.BookRouter)
                    }
                )
            }

            composable<RoutesAndEvent.RegisterRouter> {
                RegisterScreen(
                    onNavigationToLogin = {
                        navController.navigate(RoutesAndEvent.LoginRouter)
                    },
                    onNavigationRegisterSendEmail = {
                        navController.navigate(RoutesAndEvent.LoginRouter)
                    }
                )
            }



            //User
            composable<RoutesAndEvent.BookRouter> {
                BookScreen()
            }

            composable<RoutesAndEvent.ChatBotRouter> {
                ChatbotScreen()
            }

            composable<RoutesAndEvent.LocketRouter> {
                LocketScreen()
            }

            composable<RoutesAndEvent.ProfileRouter> {
                ProfileScreen()
            }
        }
    }
}