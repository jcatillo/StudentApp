package com.example.studentapp.ui.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.profile.models.NotificationSettingsUiState
import com.example.studentapp.ui.theme.DarkGreen

@Composable
fun NotificationSettingsSection(
    settings: NotificationSettingsUiState,
    onEmailNotificationsChange: (Boolean) -> Unit,
    onSmsNotificationsChange: (Boolean) -> Unit,
    onSystemAlertsChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    ProfileSectionCard(
        title = "Notification Settings",
        subtitle = "Choose how the system reaches you for updates, reminders, and security notices.",
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            NotificationToggleRow(
                title = "Email Notifications",
                description = "Receive account notices, academic reminders, and service updates by email.",
                checked = settings.emailNotifications,
                onCheckedChange = onEmailNotificationsChange
            )

            NotificationToggleRow(
                title = "SMS Notifications",
                description = "Get urgent reminders and time-sensitive account alerts through text messages.",
                checked = settings.smsNotifications,
                onCheckedChange = onSmsNotificationsChange
            )

            NotificationToggleRow(
                title = "System Alerts",
                description = "Show in-app alerts for profile changes, verification prompts, and campus notices.",
                checked = settings.systemAlerts,
                onCheckedChange = onSystemAlertsChange
            )
        }
    }
}

@Composable
private fun NotificationToggleRow(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF8FAF8),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = title,
                color = Color(0xFF0F172A),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = description,
                color = Color(0xFF475569),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = DarkGreen,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFCBD5E1)
            )
        )
    }
}
