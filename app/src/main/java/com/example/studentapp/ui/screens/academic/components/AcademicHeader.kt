package com.example.studentapp.ui.screens.academic.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.theme.BorderLight
import com.example.studentapp.ui.theme.DarkGreen
import com.example.studentapp.ui.theme.White

@Composable
fun AcademicHeader(
    onBackClick: () -> Unit
) {
    Surface(color = White) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HeaderTextAction(
                    label = "Back",
                    onClick = onBackClick
                )

                Text(
                    text = "Academic Services",
                    color = Color(0xFF0F172A),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                HeaderTextAction(
                    label = "Alerts",
                    onClick = {}
                )
            }

            HorizontalDivider(color = BorderLight)
        }
    }
}

@Composable
private fun HeaderTextAction(
    label: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(999.dp),
        color = Color(0xFFF7FAF7)
    ) {
        Text(
            text = label,
            color = DarkGreen,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
        )
    }
}
