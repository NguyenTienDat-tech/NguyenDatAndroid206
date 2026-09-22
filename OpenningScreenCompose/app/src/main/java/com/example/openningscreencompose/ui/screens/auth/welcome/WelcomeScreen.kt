package com.example.openningscreencompose.ui.screens.auth.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.openningscreencompose.R
import com.example.openningscreencompose.ui.components.AppButton

@Composable
fun WelcomeScreen(
    onNavigationToLogin: () -> Unit,
    onNavigationToRegister: () -> Unit,

    viewModel: WelcomeViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.event.collect { event ->
            when(event) {
                is WelcomeEvent.NavigationLogin -> {
                    onNavigationToLogin()
                }

                is WelcomeEvent.NavigationRegister -> {
                    onNavigationToRegister()
                }
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp),
        )

        Spacer(modifier = Modifier.height(300.dp))

        AppButton(
            text = "Sign In",
            onClick = {
                viewModel.loginClick()
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = "Create account",
            onClick = {
                viewModel.registerClick()
            }
        )
    }
}