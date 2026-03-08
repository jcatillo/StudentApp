package com.example.studentapp.ui.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge as NotificationBadge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.theme.DarkGreen
import com.example.studentapp.ui.theme.Gold
import com.example.studentapp.ui.theme.TextMuted
import com.example.studentapp.ui.theme.White

@Composable
fun DashboardHeader(
    studentName: String,
    hasUnreadNotifications: Boolean
) {
    Surface(color = White) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AvatarPlaceholder(initials = buildInitials(studentName))

                    Column {
                        Text(
                            text = "Welcome back,",
                            color = TextMuted,
                            fontSize = 12.sp
                        )

                        Text(
                            text = studentName,
                            color = Color(0xFF0F172A),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                NotificationButton(hasUnreadNotifications = hasUnreadNotifications)
            }

            HorizontalDivider(color = Color(0xFFF1F5F9))
        }
    }
}

private fun buildInitials(fullName: String): String {
    return fullName
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .joinToString(separator = "") { it.first().uppercase() }
}

@Composable
private fun AvatarPlaceholder(initials: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = Gold, shape = CircleShape)
            .background(Color(0xFFFFF7E8)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = DarkGreen,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun NotificationButton(hasUnreadNotifications: Boolean) {
    Surface(
        modifier = Modifier.size(44.dp),
        shape = CircleShape,
        color = Color(0xFFF8FAFC),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        BadgedBox(
            modifier = Modifier.fillMaxSize(),
            badge = {
                if (hasUnreadNotifications) {
                    NotificationBadge(
                        containerColor = Gold,
                        modifier = Modifier.size(10.dp)
                    )
                }
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    tint = TextMuted
                )
            }
        }
    }
}
