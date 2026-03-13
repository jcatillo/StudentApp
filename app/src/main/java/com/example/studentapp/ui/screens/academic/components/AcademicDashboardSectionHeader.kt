package com.example.studentapp.ui.screens.academic.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.academic.AcademicScreenColors

@Composable
fun AcademicDashboardSectionHeader(
    onViewAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Student Dashboard",
            color = AcademicScreenColors.Slate800,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "View All",
            modifier = Modifier.clickable(onClick = onViewAllClick),
            color = AcademicScreenColors.Primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
