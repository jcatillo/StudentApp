package com.example.studentapp.ui.screens.profile.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.theme.DarkGreen

@Composable
fun ProfileFeedbackText(
    message: String,
    modifier: Modifier = Modifier,
    color: Color = DarkGreen
) {
    Text(
        text = message,
        modifier = modifier,
        color = color,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium
    )
}
