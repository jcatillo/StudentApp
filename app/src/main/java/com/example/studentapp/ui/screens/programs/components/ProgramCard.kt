package com.example.studentapp.ui.screens.programs.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.programs.ProgramsScreenColors
import com.example.studentapp.ui.screens.programs.models.ProgramBadgeVariant
import com.example.studentapp.ui.screens.programs.models.ProgramEntry

@Composable
fun ProgramCard(
    entry: ProgramEntry,
    onDownloadProspectusClick: () -> Unit,
    onViewProgramClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = ProgramsScreenColors.White,
        border = BorderStroke(1.dp, ProgramsScreenColors.Slate200),
        shadowElevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = entry.title,
                    modifier = Modifier.weight(1f),
                    color = ProgramsScreenColors.Primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp
                )

                ProgramStatusBadge(
                    text = entry.badgeText,
                    variant = entry.badgeVariant,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Schedule,
                    contentDescription = null,
                    tint = ProgramsScreenColors.Secondary
                )

                Text(
                    text = entry.scheduleLine,
                    color = ProgramsScreenColors.Secondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = entry.description,
                modifier = Modifier.padding(top = 12.dp),
                color = ProgramsScreenColors.Slate600,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProgramsPrimaryActionButton(
                    text = "Download Prospectus",
                    icon = Icons.Outlined.Download,
                    onClick = onDownloadProspectusClick,
                    modifier = Modifier.weight(1f)
                )

                ProgramsSecondaryActionButton(
                    text = "View",
                    onClick = onViewProgramClick
                )
            }
        }
    }
}

@Composable
fun ProgramStatusBadge(
    text: String,
    variant: ProgramBadgeVariant,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (variant == ProgramBadgeVariant.Accent) {
        ProgramsScreenColors.Accent.copy(alpha = 0.10f)
    } else {
        ProgramsScreenColors.Primary.copy(alpha = 0.10f)
    }
    val borderColor = if (variant == ProgramBadgeVariant.Accent) {
        ProgramsScreenColors.Accent.copy(alpha = 0.20f)
    } else {
        ProgramsScreenColors.Primary.copy(alpha = 0.20f)
    }
    val textColor = if (variant == ProgramBadgeVariant.Accent) {
        ProgramsScreenColors.Accent
    } else {
        ProgramsScreenColors.Primary
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        color = backgroundColor,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(backgroundColor)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = text.uppercase(),
                color = textColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}
