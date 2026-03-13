package com.example.studentapp.ui.screens.programs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.screens.programs.models.Program
import com.example.studentapp.ui.screens.programs.models.ProgramStatusType

private val CardDark = Color(0xFF0E2A1B)
private val CardDarker = Color(0xFF102417)
private val BorderColor = Color(0xFF173824)
private val TextSoft = Color(0xFFB8C7BD)
private val Green = Color(0xFF34C759)
private val Gold = Color(0xFFF3B431)
private val GrayArrow = Color(0xFFA7B0AA)

@Composable
fun ProgramCard(
    program: Program,
    modifier: Modifier = Modifier
) {
    val arrowTint = when (program.statusType) {
        ProgramStatusType.OPEN -> Gold
        ProgramStatusType.CLOSING_SOON -> Color(0xFF355E45)
        ProgramStatusType.NEXT_INTAKE -> GrayArrow
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        CardDark,
                        CardDarker,
                        Color(0xFF19311F)
                    )
                )
            )
            .border(
                width = 1.dp,
                color = BorderColor,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = program.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                ProgramBadge(
                    text = program.badge,
                    type = program.badgeType
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            ProgramDurationRow(program = program)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = program.description,
                color = TextSoft
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProgramStatusRow(program = program)

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open program",
                    tint = arrowTint
                )
            }
        }
    }
}