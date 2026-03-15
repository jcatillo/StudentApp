@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.studentapp.ui.screens.grades

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.components.StudentBottomNavBar
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.screens.grades.components.GradesFilterChipRow
import com.example.studentapp.ui.screens.grades.components.GradesHeaderSummaryCard
import com.example.studentapp.ui.screens.grades.components.SubjectGradeCard
import com.example.studentapp.ui.screens.grades.models.GradeSemesterFilter
import com.example.studentapp.ui.screens.grades.models.GradesSubjectItem
import com.example.studentapp.ui.screens.grades.models.SubjectIconType
import com.example.studentapp.ui.screens.grades.models.SubjectStatus

@Composable
@Preview
fun GradesScreen(
    navigationItems: List<StudentBottomNavItem> = emptyList(),
    selectedNavItemId: String = "",
    onBottomNavSelected: (StudentBottomNavItem) -> Unit = {},
    onBackClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    onDownloadClick: () -> Unit = {}
) {
    var filters by remember {
        mutableStateOf(
            listOf(
                GradeSemesterFilter("1st Sem 3rd Year", true),
                GradeSemesterFilter("3rd Sem 2nd Year", false),
                GradeSemesterFilter("2nd Sem 2nd Year", false)
            )
        )
    }

    val subjects = remember {
        listOf(
            GradesSubjectItem(
                title = "Advanced Algorithms",
                codeCredits = "CS501 • 4 Credits",
                gradePoint = "4.0",
                status = SubjectStatus.COMPLETED,
                iconType = SubjectIconType.CODE
            ),
            GradesSubjectItem(
                title = "Database Management",
                codeCredits = "CS505 • 3 Credits",
                gradePoint = "3.7",
                status = SubjectStatus.COMPLETED,
                iconType = SubjectIconType.DATABASE
            ),
            GradesSubjectItem(
                title = "Artificial Intelligence",
                codeCredits = "CS502 • 4 Credits",
                gradePoint = "3.5",
                status = SubjectStatus.COMPLETED,
                iconType = SubjectIconType.AI
            ),
            GradesSubjectItem(
                title = "UI/UX Principles",
                codeCredits = "DS506 • 3 Credits",
                gradePoint = "--",
                status = SubjectStatus.IN_PROGRESS,
                iconType = SubjectIconType.DESIGN
            )
        )
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Grades",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onMoreClick) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = "More"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            StudentBottomNavBar(
                items = navigationItems,
                selectedItemId = selectedNavItemId,
                onItemSelected = onBottomNavSelected
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onDownloadClick,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ) {
                Icon(
                    imageVector = Icons.Outlined.FileDownload,
                    contentDescription = "Download Grades",
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                GradesHeaderSummaryCard(
                    gpa = "3.85",
                    academicLevel = "Junior"
                )
            }

            item {
                GradesFilterChipRow(
                    filters = filters,
                    onFilterClick = { clicked ->
                        filters = filters.map {
                            it.copy(isSelected = it.label == clicked)
                        }
                    }
                )
            }

            item {
                Text(
                    text = "CURRENT ENROLLED SUBJECTS",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )
            }

            items(subjects) { subject ->
                SubjectGradeCard(item = subject)
            }
        }
    }
}
