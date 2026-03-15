@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.studentapp.ui.screens.studyload

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.components.StudentBottomNavBar
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.screens.studyload.components.StudyLoadSubjectCard
import com.example.studentapp.ui.screens.studyload.components.StudyLoadSummaryCard
import com.example.studentapp.ui.screens.studyload.models.StudyLoadItem

@Composable
@Preview
fun StudyLoadScreen(
    navigationItems: List<StudentBottomNavItem> = emptyList(),
    selectedNavItemId: String = "",
    onBottomNavSelected: (StudentBottomNavItem) -> Unit = {},
    onBackClick: () -> Unit = {},
    onDownloadClick: () -> Unit = {}
) {
    val subjects = listOf(
        StudyLoadItem(
            title = "Digital Illustration",
            code = "CS301",
            schedule = "T-TH 10:00 - 12:00",
            room = "Art Studio",
            instructor = "Prof. Sarah Lee",
            units = 3,
            status = "Enrolled"
        ),
        StudyLoadItem(
            title = "Technical Writing",
            code = "ENG202",
            schedule = "M-W-F 1:00 - 2:00",
            room = "Room 205",
            instructor = "Prof. Michael Chen",
            units = 3,
            status = "Enrolled"
        ),
        StudyLoadItem(
            title = "Software Engineering",
            code = "CS401",
            schedule = "M-W 3:00 - 5:00",
            room = "CS Lab 1",
            instructor = "Prof. David Park",
            units = 3,
            status = "Enrolled"
        ),
        StudyLoadItem(
            title = "Team Sports",
            code = "PE201",
            schedule = "SAT 10:00 - 12:00",
            room = "Gymnasium",
            instructor = "Coach Emma Wilson",
            units = 3,
            status = "Confirmed"
        )
    )

    Scaffold(
        containerColor = StudyLoadScreenColors.Background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Study Load",
                        color = StudyLoadScreenColors.TextDark,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Back",
                            tint = StudyLoadScreenColors.TextDark
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = StudyLoadScreenColors.Background
                )
            )
        },
        bottomBar = {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(StudyLoadScreenColors.Background)
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Column {
                        Button(
                            onClick = onDownloadClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            contentPadding = PaddingValues()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            listOf(
                                                StudyLoadScreenColors.DownloadButtonStart,
                                                StudyLoadScreenColors.DownloadButtonEnd
                                            )
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.foundation.layout.Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.FileDownload,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                                    Text(
                                        text = "Download Study Load PDF",
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = StudyLoadScreenColors.InfoBox
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Your study load is officially confirmed. You can download this for your records.",
                                modifier = Modifier.padding(14.dp),
                                color = StudyLoadScreenColors.InfoText,
                                fontSize = 13.sp
                            )
                        }
                    }
                }

                StudentBottomNavBar(
                    items = navigationItems,
                    selectedItemId = selectedNavItemId,
                    onItemSelected = onBottomNavSelected
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(StudyLoadScreenColors.Background)
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                StudyLoadSummaryCard(
                    totalUnits = 12,
                    semester = "1st, 2024-2025",
                    courseCount = 4
                )
            }

            item {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Current Semester Load",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = StudyLoadScreenColors.TextDark
                )
            }

            items(subjects) { subject ->
                StudyLoadSubjectCard(item = subject)
            }

            item {
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}
