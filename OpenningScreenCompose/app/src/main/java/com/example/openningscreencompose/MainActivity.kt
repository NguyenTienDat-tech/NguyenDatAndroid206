package com.example.openningscreencompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.openningscreencompose.ui.navigation.AppNavigation
import com.example.openningscreencompose.ui.screens.auth.login.LoginScreen
import com.example.openningscreencompose.ui.screens.auth.register.RegisterScreen
import com.example.openningscreencompose.ui.screens.home_user.book.BookScreen
import com.example.openningscreencompose.ui.theme.OpenningScreenComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpenningScreenComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        AppNavigation()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    OpenningScreenComposeTheme {
        BookScreen()
    }
}