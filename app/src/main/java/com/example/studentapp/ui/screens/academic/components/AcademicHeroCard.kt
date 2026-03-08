package com.example.studentapp.ui.screens.academic.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.theme.DarkGreen
import com.example.studentapp.ui.theme.Gold
import com.example.studentapp.ui.theme.White

@Composable
fun AcademicHeroCard(
    studentName: String,
    programSummary: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(164.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        DarkGreen,
                        Color(0xFF2B7A31)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
    ) {
        HeroBackdrop()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Welcome back",
                color = Gold,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )

            Text(
                text = studentName,
                color = White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = programSummary,
                color = White.copy(alpha = 0.82f),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
private fun HeroBackdrop() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(120.dp)
                .background(
                    color = White.copy(alpha = 0.08f),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(96.dp)
                .height(56.dp)
                .background(
                    color = White.copy(alpha = 0.06f),
                    shape = RoundedCornerShape(18.dp)
                )
        )
    }
}
