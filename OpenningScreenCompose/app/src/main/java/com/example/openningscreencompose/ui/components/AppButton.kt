package com.example.openningscreencompose.ui.components

import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.openningscreencompose.ui.theme.AppTheme
import com.example.openningscreencompose.ui.theme.color_background
import com.example.openningscreencompose.ui.theme.color_primary

@Composable
fun AppButton (
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp),
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(1.dp, color_primary),
        colors = ButtonDefaults.buttonColors(
            containerColor = color_primary,
            contentColor = color_background
        ),
    ) {
        Text(text = text, style = AppTheme.typography.nut2)
    }
}