package com.example.studentapp.ui.screens.programs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.programs.ProgramsScreenColors
import com.example.studentapp.ui.screens.programs.models.ProgramsTab

@Composable
fun ProgramsTabs(
    selectedTab: ProgramsTab,
    onTabSelected: (ProgramsTab) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(ProgramsTab.entries) { tab ->
            ProgramsTabItem(
                tab = tab,
                isSelected = tab == selectedTab,
                onClick = { onTabSelected(tab) }
            )
        }
    }
}

@Composable
fun ProgramsTabItem(
    tab: ProgramsTab,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tab.label,
            color = if (isSelected) {
                ProgramsScreenColors.Primary
            } else {
                ProgramsScreenColors.Slate500
            },
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
        )

        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .width(48.dp)
                .background(
                    color = if (isSelected) {
                        ProgramsScreenColors.Primary
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(2.dp)
                )
                .padding(vertical = 1.dp)
        )
    }
}
