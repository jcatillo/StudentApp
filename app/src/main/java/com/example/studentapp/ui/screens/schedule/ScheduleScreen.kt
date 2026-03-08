package com.example.studentapp.ui.screens.schedule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.schedule.models.ScheduleDaySection
import com.example.studentapp.ui.screens.schedule.models.ScheduleEntry
import com.example.studentapp.ui.screens.schedule.models.ScheduleUiState
import com.example.studentapp.ui.screens.schedule.models.buildScheduleUiState
import com.example.studentapp.ui.theme.BackgroundLight
import com.example.studentapp.ui.theme.BorderLight
import com.example.studentapp.ui.theme.DarkGreen
import com.example.studentapp.ui.theme.Gold
import com.example.studentapp.ui.theme.PrimaryTint
import com.example.studentapp.ui.theme.StudentAppTheme
import com.example.studentapp.ui.theme.TextMuted
import com.example.studentapp.ui.theme.White

@Composable
fun ScheduleScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: ScheduleUiState = buildScheduleUiState()
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = BackgroundLight,
        topBar = {
            ScheduleHeader(
                studentName = state.studentName,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 24.dp,
                top = innerPadding.calculateTopPadding() + 16.dp,
                end = 24.dp,
                bottom = innerPadding.calculateBottomPadding() + 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(state.sections) { section ->
                ScheduleDaySectionCard(section = section)
            }
        }
    }
}

@Composable
private fun ScheduleHeader(
    studentName: String,
    onBackClick: () -> Unit
) {
    Surface(color = White) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = DarkGreen
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Class Schedule",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )

                    Text(
                        text = studentName,
                        color = TextMuted,
                        fontSize = 13.sp
                    )
                }
            }

            HorizontalDivider(color = Color(0xFFF1F5F9))
        }
    }
}

@Composable
private fun ScheduleDaySectionCard(section: ScheduleDaySection) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = section.dayLabel,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color(0xFF0F172A)
        )

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            section.entries.forEach { entry ->
                ScheduleEntryCard(entry = entry)
            }
        }
    }
}

@Composable
private fun ScheduleEntryCard(entry: ScheduleEntry) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        border = BorderStroke(1.dp, BorderLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(40.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = PrimaryTint
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = DarkGreen
                        )
                    }
                }

                Column {
                    Text(
                        text = entry.courseTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF0F172A)
                    )

                    Text(
                        text = entry.courseCode,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Gold
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                ScheduleMetaRow(label = "Time", value = entry.timeRange)
                ScheduleMetaRow(label = "Room", value = entry.room)
                ScheduleMetaRow(label = "Instructor", value = entry.instructor)
            }
        }
    }
}

@Composable
private fun ScheduleMetaRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8FAFC), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = TextMuted,
            fontSize = 12.sp
        )

        Text(
            text = value,
            color = Color(0xFF0F172A),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScheduleScreenPreview() {
    StudentAppTheme(dynamicColor = false) {
        ScheduleScreen(onBackClick = {})
    }
}
