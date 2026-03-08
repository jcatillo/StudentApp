package com.example.studentapp.ui.screens.services.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.studentapp.ui.screens.services.models.Complaint
import com.example.studentapp.ui.theme.DarkGreen

@Composable
fun StudentAffairsSection(
    complaints: List<Complaint>,
    onSubmitClick: () -> Unit = {}
) {
    Column {
        Text(
            "Student Affairs (SAO)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
            Column {
                // Header row
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = DarkGreen.copy(alpha = 0.05f)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Default.Warning,
                                contentDescription = null,
                                tint = Color(0xFFD4AF37),
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                "New Complaint/Request",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Button(
                            onClick = onSubmitClick,
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = DarkGreen),
                            contentPadding = ButtonDefaults.ContentPadding
                        ) {
                            Text(
                                "Submit",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Body
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Track the status of your existing student grievances or special requests.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    complaints.forEachIndexed { index, complaint ->
                        ComplaintItem(
                            complaint = complaint,
                            showDivider = index < complaints.lastIndex
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ComplaintItem(
    complaint: Complaint,
    showDivider: Boolean
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    complaint.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    complaint.status.label,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = complaint.status.color,
                    letterSpacing = 1.5.sp
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More options",
                    tint = DarkGreen.copy(alpha = 0.4f)
                )
            }
        }

        if (showDivider) {
            Divider(color = DarkGreen.copy(alpha = 0.1f))
        }
    }
}
