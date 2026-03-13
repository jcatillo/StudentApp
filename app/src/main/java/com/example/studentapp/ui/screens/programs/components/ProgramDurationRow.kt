package com.example.studentapp.ui.screens.programs.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.screens.programs.models.Program

@Composable
fun ProgramDurationRow(program: Program) {
    val durationText = when {
        program.durationYears != null -> "${program.durationYears} Years"
        program.durationMonths != null -> "${program.durationMonths} Months"
        else -> "N/A"
    }

    Row {
        Text(
            text = "●",
            color = Color(0xFF1F8B4C)
        )
        Spacer(modifier = androidx.compose.ui.Modifier.width(6.dp))
        Text(
            text = durationText,
            color = Color(0xFF5E8F6B)
        )
    }
}