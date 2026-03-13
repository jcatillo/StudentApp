package com.example.studentapp.ui.screens.services.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.services.models.DocumentType
import com.example.studentapp.ui.theme.DarkGreen

@Composable
fun DocumentRequestsSection(activeRequestCount: Int = 3) {
    Column {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Document Requests",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = DarkGreen.copy(alpha = 0.2f)
            ) {
                Text(
                    "$activeRequestCount Active Requests",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkGreen
                )
            }
        }

        // Timeline Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Step 1 - Processing (active)
                TimelineStep(
                    icon = Icons.Default.Schedule,
                    iconTint = Color(0xFFD4AF37),
                    title = "Processing",
                    subtitle = "Transcript of Records (TOR) - Submitted Oct 10",
                    isActive = true,
                    showLine = true
                )

                // Step 2 - Accepted (inactive)
                TimelineStep(
                    icon = Icons.Default.CheckCircle,
                    iconTint = DarkGreen.copy(alpha = 0.4f),
                    title = "Accepted",
                    subtitle = "Verified by Registrar",
                    isActive = false,
                    showLine = true
                )

                // Step 3 - Ready for Pickup (inactive)
                TimelineStep(
                    icon = Icons.Default.Inventory2,
                    iconTint = DarkGreen.copy(alpha = 0.4f),
                    title = "Ready for Pickup",
                    subtitle = "Counter 4, Admin Building",
                    isActive = false,
                    showLine = false
                )
            }
        }
    }
}

@Composable
private fun TimelineStep(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    title: String,
    subtitle: String,
    isActive: Boolean,
    showLine: Boolean
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // Icon + line column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(40.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
            if (showLine) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(32.dp)
                        .padding(vertical = 4.dp)
                ) {
                    Divider(
                        modifier = Modifier
                            .width(2.dp)
                            .height(32.dp),
                        color = DarkGreen.copy(alpha = 0.3f)
                    )
                }
            }
        }

        // Text column
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = if (showLine) 16.dp else 0.dp)
        ) {
            Text(
                title,
                fontSize = 14.sp,
                fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Medium,
                color = if (isActive) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                subtitle,
                fontSize = 12.sp,
                color = if (isActive) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun DocumentTypeGrid(
    documentTypes: List<DocumentType>,
    onDocumentTypeClick: (DocumentType) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        documentTypes.forEach { docType ->
            DocumentTypeCard(
                docType = docType,
                onClick = { onDocumentTypeClick(docType) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun DocumentTypeCard(
    docType: DocumentType,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = docType.icon,
                contentDescription = null,
                tint = docType.iconTint,
                modifier = Modifier.size(24.dp)
            )
            Text(
                docType.label,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
