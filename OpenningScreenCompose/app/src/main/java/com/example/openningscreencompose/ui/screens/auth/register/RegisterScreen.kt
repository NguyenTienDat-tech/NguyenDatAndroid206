package com.example.openningscreencompose.ui.screens.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.openningscreencompose.R
import com.example.openningscreencompose.data.remote.api.ApiAuth
import com.example.openningscreencompose.data.remote.retrofitInstance.RetrofitInstance
import com.example.openningscreencompose.data.repository.AuthRepository
import com.example.openningscreencompose.ui.components.AppButton
import com.example.openningscreencompose.ui.components.AppTextField
import com.example.openningscreencompose.ui.theme.AppTheme
import com.example.openningscreencompose.ui.theme.color_error
import com.example.openningscreencompose.ui.theme.color_primary
import com.example.openningscreencompose.ui.theme.color_text_tittle

@Composable
fun RegisterScreen(
    onNavigationToLogin: () -> Unit,
    onNavigationRegisterSendEmail: () -> Unit,

    viewModel: RegisterViewModel = viewModel(
        factory = RegisterViewModelFactory(
            AuthRepository(
                RetrofitInstance.retrofit.create(ApiAuth::class.java)
            )
        )
    )
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.event.collect { event ->
            when (event) {
                is RegisterEvent.NavigationLogin -> {
                    onNavigationToLogin()
                }

                is RegisterEvent.NavigationRegisterSendEmail -> {
                    onNavigationRegisterSendEmail()
                }
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            style = AppTheme.typography.chaoMung,
            text = "Đăng ký",
            color = color_text_tittle,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppTextField(
            label = "Tên đăng nhập",
            placeholder = "Nhập tên đăng nhập",
            value = state.name,
            onValueChange = { newValue ->
                viewModel.onNameChange(newValue)
            },
            isError = state.isName
        )

        if (state.isName && state.nameError.isNotBlank()) {
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                style = AppTheme.typography.chu2,
                text = state.nameError,
                color = color_error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            label = "Email",
            placeholder = "Nhập email",
            value = state.email,
            onValueChange = { newValue ->
                viewModel.onEmailChange(newValue)
            },
            isError = state.isEmail
        )

        if (state.isEmail && state.emailError.isNotBlank()) {
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                style = AppTheme.typography.chu2,
                text = state.emailError,
                color = color_error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            label = "Mật khẩu",
            placeholder = "Mật khẩu phải có 8 ký tự trở lên",
            value = state.password,
            onValueChange = { newValue ->
                viewModel.onPasswordChange(newValue)
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = state.isPassword
        )

        if (state.isPassword && state.passwordError.isNotBlank()) {
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                style = AppTheme.typography.chu2,
                text = state.passwordError,
                color = color_error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        AppTextField(
            label = "Nhập lại mật khẩu",
            placeholder = "Nhập mật khẩu",
            value = state.password1,
            onValueChange = { newValue ->
                viewModel.onPasswordChange1(newValue)
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = state.isPassword1
        )

        if (state.isPassword1 && state.passwordError1.isNotBlank()) {
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                style = AppTheme.typography.chu2,
                text = state.passwordError1,
                color = color_error
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        AppButton(
            text = "Đăng ký",
            onClick = {
                viewModel.onOtpClick()
            },
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                style = AppTheme.typography.chu1,
                text = "Chưa có tài khoản?",
                fontSize = 14.sp,
                color = color_text_tittle
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                style = AppTheme.typography.chu1,
                text = "Đăng nhập",
                fontSize = 14.sp,
                color = color_primary,
                modifier = Modifier.clickable {
                    viewModel.loginClick()
                }
            )
        }
    }
}