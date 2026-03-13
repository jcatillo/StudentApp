package com.example.studentapp.ui.screens.programs.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.studentapp.ui.screens.programs.models.Program
import com.example.studentapp.ui.screens.programs.models.ProgramStatusType

@Composable
fun ProgramStatusRow(program: Program) {
    val color = when (program.statusType) {
        ProgramStatusType.OPEN -> Color(0xFFF3B431)
        ProgramStatusType.CLOSING_SOON -> Color(0xFF5E7E68)
        ProgramStatusType.NEXT_INTAKE -> Color(0xFF78928A)
    }

    Text(
        text = program.statusLabel,
        color = color,
        fontWeight = if (program.statusType == ProgramStatusType.OPEN) FontWeight.Bold else FontWeight.Normal,
        fontStyle = if (program.statusType == ProgramStatusType.CLOSING_SOON) FontStyle.Italic else FontStyle.Normal
    )
}