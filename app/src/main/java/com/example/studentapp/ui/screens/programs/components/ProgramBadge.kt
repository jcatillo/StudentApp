package com.example.studentapp.ui.screens.programs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.screens.programs.models.ProgramBadgeType

@Composable
fun ProgramBadge(
    text: String,
    type: ProgramBadgeType
) {
    val brush = when (type) {
        ProgramBadgeType.UNDERGRAD -> Brush.horizontalGradient(
            listOf(Color(0xFF7A681C), Color(0xFFD2B443))
        )
        ProgramBadgeType.POSTGRAD -> Brush.horizontalGradient(
            listOf(Color(0xFF213123), Color(0xFF344B37))
        )
        ProgramBadgeType.SHORT_COURSE -> Brush.horizontalGradient(
            listOf(Color(0xFF7A681C), Color(0xFFD2B443))
        )
    }

    Surface(
        shape = RoundedCornerShape(50),
        color = Color.Transparent
    ) {
        Text(
            text = text,
            color = if (type == ProgramBadgeType.POSTGRAD) Color(0xFF8E9C91) else Color(0xFFFFF0A8),
            fontWeight = FontWeight.Bold,
            modifier = androidx.compose.ui.Modifier
                .background(brush = brush, shape = RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}