package com.example.openningscreencompose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.openningscreencompose.ui.theme.AppTheme
import com.example.openningscreencompose.ui.theme.color_error
import com.example.openningscreencompose.ui.theme.color_primary
import com.example.openningscreencompose.ui.theme.color_text_hint
import com.example.openningscreencompose.ui.theme.color_text_tittle

@Composable
fun AppTextField (
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = AppTheme.typography.chu1, color = color_text_tittle)

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            placeholder = {
                Text(placeholder, style = AppTheme.typography.chu1, color = color_text_hint)
            },
            textStyle = AppTheme.typography.chu2.copy(color = if (isError) { color_error } else { color_text_tittle }),
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(200.dp),
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                //trạng thái bình thường
                focusedBorderColor = color_primary,
                unfocusedBorderColor = color_text_hint,
                focusedTextColor = color_text_tittle,
                unfocusedTextColor = color_text_tittle,

                //trạng thái khi lỗi
                errorBorderColor = color_error,
                errorTextColor = color_text_tittle,
                errorCursorColor = color_error
            )
        )
    }
}