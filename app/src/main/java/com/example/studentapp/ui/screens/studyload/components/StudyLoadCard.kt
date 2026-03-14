package com.example.studentapp.ui.screens.studyload.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.studyload.StudyLoadScreenColors
import com.example.studentapp.ui.screens.studyload.models.StudyLoadItem

@Composable
fun StudyLoadSubjectCard(
    item: StudyLoadItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = StudyLoadScreenColors.CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = item.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = StudyLoadScreenColors.TextDark
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        StatusBadge(status = item.status)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.code,
                        fontSize = 13.sp,
                        color = StudyLoadScreenColors.TextMuted
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Units",
                        fontSize = 13.sp,
                        color = StudyLoadScreenColors.TextMuted
                    )
                    Text(
                        text = item.units.toString(),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium,
                        color = StudyLoadScreenColors.TextDark
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.AccessTime,
                    contentDescription = null,
                    tint = StudyLoadScreenColors.TextMuted,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = item.schedule,
                    fontSize = 13.sp,
                    color = StudyLoadScreenColors.TextDark
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = androidx.compose.ui.graphics.Color(0xFFD94A7A),
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = item.room,
                        fontSize = 13.sp,
                        color = StudyLoadScreenColors.TextDark
                    )
                }

                Text(
                    text = item.instructor,
                    fontSize = 13.sp,
                    color = StudyLoadScreenColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun StatusBadge(status: String) {
    val bgColor = if (status.equals("Confirmed", true)) {
        StudyLoadScreenColors.GreenBadge
    } else {
        StudyLoadScreenColors.YellowBadge
    }

    val textColor = if (status.equals("Confirmed", true)) {
        androidx.compose.ui.graphics.Color(0xFF4A8A4D)
    } else {
        androidx.compose.ui.graphics.Color.White
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(bgColor)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = status,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = textColor
        )
    }
}

@Composable
fun StudyLoadSummaryCard(
    totalUnits: Int,
    semester: String,
    courseCount: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        StudyLoadScreenColors.PrimaryGreen,
                        androidx.compose.ui.graphics.Color(0xFF6D9954)
                    )
                )
            )
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(42.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(androidx.compose.ui.graphics.Color.White.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "↓",
                color = androidx.compose.ui.graphics.Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Text(
                text = "Total Units",
                color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp
            )

            Text(
                text = totalUnits.toString(),
                color = androidx.compose.ui.graphics.Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 42.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row {
                Column {
                    Text(
                        text = "Semester",
                        color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.85f),
                        fontSize = 13.sp
                    )
                    Text(
                        text = semester,
                        color = androidx.compose.ui.graphics.Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.width(24.dp))

                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(32.dp)
                        .background(androidx.compose.ui.graphics.Color.White.copy(alpha = 0.35f))
                )

                Spacer(modifier = Modifier.width(24.dp))

                Column {
                    Text(
                        text = "Courses",
                        color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.85f),
                        fontSize = 13.sp
                    )
                    Text(
                        text = "$courseCount subjects",
                        color = androidx.compose.ui.graphics.Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(100.dp)
                .clip(CircleShape)
                .background(androidx.compose.ui.graphics.Color.White.copy(alpha = 0.10f))
        )
    }
}
