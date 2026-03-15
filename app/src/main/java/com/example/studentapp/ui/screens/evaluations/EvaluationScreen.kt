@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.studentapp.ui.screens.evaluations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.studentapp.ui.screens.evaluations.components.EvaluationCourseCard
import com.example.studentapp.ui.screens.evaluations.components.EvaluationPendingSectionHeader
import com.example.studentapp.ui.screens.evaluations.components.EvaluationReviewCard
import com.example.studentapp.ui.screens.evaluations.models.EvaluationCourseIconType
import com.example.studentapp.ui.screens.evaluations.models.EvaluationCourseItem

@Composable
@Preview
fun EvaluationScreen(
    navigationItems: List<StudentBottomNavItem> = emptyList(),
    selectedNavItemId: String = "",
    onBottomNavSelected: (StudentBottomNavItem) -> Unit = {},
    onBackClick: () -> Unit = {},
    onSubmitClick: () -> Unit = {}
) {
    var teachingQuality by remember { mutableStateOf(4) }
    var courseMaterials by remember { mutableStateOf(5) }
    var punctuality by remember { mutableStateOf(3) }
    var comments by remember { mutableStateOf("") }

    val pendingCourses = remember {
        listOf(
            EvaluationCourseItem(
                codeTitle = "CS301 - Data Structures",
                instructor = "Prof. Robert Smith",
                iconType = EvaluationCourseIconType.DOCUMENT
            ),
            EvaluationCourseItem(
                codeTitle = "DS105 - Statistics",
                instructor = "Dr. Michael Chen",
                iconType = EvaluationCourseIconType.CHART
            )
        )
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Faculty & Course Evaluation",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
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
                EvaluationPendingSectionHeader(
                    title = "Pending Evaluations",
                    remainingText = "2 REMAINING"
                )
            }

            items(pendingCourses) { course ->
                EvaluationCourseCard(item = course)
            }

            item {
                EvaluationReviewCard(
                    courseTitle = "Advanced Algorithms",
                    instructor = "Dr. Sarah Jenkins",
                    teachingQuality = teachingQuality,
                    courseMaterials = courseMaterials,
                    punctuality = punctuality,
                    comments = comments,
                    onTeachingQualityChanged = { teachingQuality = it },
                    onCourseMaterialsChanged = { courseMaterials = it },
                    onPunctualityChanged = { punctuality = it },
                    onCommentsChanged = { comments = it },
                    onSubmitClick = onSubmitClick
                )
            }
        }
    }
}
