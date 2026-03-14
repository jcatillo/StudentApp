package com.example.studentapp.ui.screens.enrollment.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.enrollment.EnrollmentScreenColors
import com.example.studentapp.ui.screens.enrollment.models.EnrollableCourse

@Composable
fun EnrollmentCourseStepContent(
    courses: List<EnrollableCourse>,
    selectedCourseCodes: List<String>,
    searchQuery: String,
    contentPadding: PaddingValues,
    onSearchQueryChange: (String) -> Unit,
    onCourseToggle: (EnrollableCourse) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Select Courses",
                color = EnrollmentScreenColors.Slate900,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Fall Semester 2024 • Year 3",
                modifier = Modifier.padding(top = 4.dp),
                color = EnrollmentScreenColors.Slate500,
                fontSize = 14.sp
            )
        }

        item {
            EnrollmentCourseSearchBar(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        items(
            items = courses,
            key = { it.code }
        ) { course ->
            EnrollmentCourseCard(
                course = course,
                isSelected = selectedCourseCodes.contains(course.code),
                onClick = { onCourseToggle(course) }
            )
        }
    }
}
