package com.example.studentapp.ui.screens.goodmoral.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RequestHistorySection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Request History",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            TextButton(onClick = { /* View All */ }) {
                Text(
                    text = "View All",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            HistoryItem(
                title = "Scholarship App",
                reference = "#GMC-2024-0892",
                date = "Oct 24, 2023",
                status = "Pending",
                statusColor = MaterialTheme.colorScheme.secondary,
                icon = Icons.Default.PendingActions,
                iconTint = MaterialTheme.colorScheme.secondary
            )
            HistoryItem(
                title = "Internship Requirement",
                reference = "#GMC-2023-0412",
                date = "Aug 12, 2023",
                status = "Ready",
                statusColor = Color(0xFF16A34A),
                icon = Icons.Default.CheckCircle,
                iconTint = Color(0xFF16A34A),
                showDownload = true
            )
            HistoryItem(
                title = "Employment Check",
                reference = "#GMC-2022-0115",
                date = "Mar 05, 2022",
                status = "Archived",
                statusColor = MaterialTheme.colorScheme.onSurfaceVariant,
                icon = Icons.Default.HistoryEdu,
                iconTint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.alpha(0.75f)
            )
        }
    }
}

@Composable
private fun HistoryItem(
    title: String,
    reference: String,
    date: String,
    status: String,
    statusColor: Color,
    icon: ImageVector,
    iconTint: Color,
    showDownload: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                RoundedCornerShape(12.dp)
            )
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Icon Container
        Column(
            modifier = Modifier
                .background(iconTint.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
        }

        // Content
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                StatusBadge(text = status, color = statusColor)
            }
            Text(
                text = "Ref: $reference • $date",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        if (showDownload) {
            IconButton(onClick = { /* Download */ }) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = "Download",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun StatusBadge(text: String, color: Color) {
    Text(
        text = text.uppercase(),
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = Modifier
            .background(color.copy(alpha = 0.1f), CircleShape)
            .border(1.dp, color.copy(alpha = 0.2f), CircleShape)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}
