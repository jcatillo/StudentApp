package com.example.studentapp.ui.screens.programs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProgramFilterRow(
    filters: List<String>,
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        filters.forEach { filter ->
            val isSelected = selectedFilter == filter

            Text(
                text = filter,
                color = if (isSelected) Color(0xFF1A1A1A) else Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(
                        color = if (isSelected) Color(0xFFF3B431) else Color(0xFF173824),
                        shape = RoundedCornerShape(50)
                    )
                    .clickable { onFilterSelected(filter) }
                    .padding(horizontal = 18.dp, vertical = 10.dp)
            )
        }
    }
}