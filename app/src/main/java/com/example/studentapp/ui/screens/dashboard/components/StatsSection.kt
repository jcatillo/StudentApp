package com.example.studentapp.ui.screens.dashboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.dashboard.models.DashboardStat
import com.example.studentapp.ui.theme.BorderLight
import com.example.studentapp.ui.theme.DarkGreen
import com.example.studentapp.ui.theme.Gold
import com.example.studentapp.ui.theme.TextMuted
import com.example.studentapp.ui.theme.White

@Composable
fun StatsSection(stats: List<DashboardStat>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(end = 8.dp)
    ) {
        items(stats) { stat ->
            StatCard(stat = stat)
        }
    }
}

@Composable
private fun StatCard(stat: DashboardStat) {
    val containerColor = if (stat.isHighlighted) DarkGreen else White
    val borderColor = if (stat.isHighlighted) DarkGreen else BorderLight
    val valueColor = if (stat.isHighlighted) White else Color(0xFF0F172A)
    val labelColor = if (stat.isHighlighted) White.copy(alpha = 0.8f) else TextMuted

    Card(
        modifier = Modifier.width(160.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = BorderStroke(1.dp, borderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (stat.isHighlighted) 6.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = stat.icon,
                contentDescription = stat.label,
                tint = Gold
            )

            Text(
                text = stat.value,
                color = valueColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stat.label,
                color = labelColor,
                fontSize = 12.sp
            )
        }
    }
}
