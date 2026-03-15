package com.example.studentapp.ui.screens.courses.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.courses.models.CourseStatus

@Composable
fun CoursesHeaderSection(
    searchQuery: String,
    selectedStatus: CourseStatus,
    onBackClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onStatusSelected: (CourseStatus) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoursesIconButton(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Back",
                onClick = onBackClick
            )

            Text(
                text = "My Courses",
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.27).sp,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                CoursesIconButton(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications",
                    onClick = {}
                )
            }
        }

        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(top = 72.dp)
        ) {
            CoursesSearchBar(
                value = searchQuery,
                onValueChange = onSearchQueryChange
            )

            CoursesTabs(
                selectedStatus = selectedStatus,
                onStatusSelected = onStatusSelected
            )
        }
    }
}
