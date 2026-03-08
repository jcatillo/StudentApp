package com.example.studentapp.ui.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.profile.models.ProfileSectionDestination
import com.example.studentapp.ui.theme.DarkGreen
import androidx.compose.material3.MaterialTheme

@Composable
fun ProfileShortcutSection(
    destinations: List<ProfileSectionDestination>,
    onDestinationClick: (ProfileSectionDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    ProfileSectionCard(
        title = "Shortcuts",
        subtitle = "Open the exact profile task you need without scrolling through every card.",
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            destinations.forEach { destination ->
                ProfileShortcutRow(
                    destination = destination,
                    onClick = {
                        onDestinationClick(destination)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProfileShortcutRow(
    destination: ProfileSectionDestination,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = destination.title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = destination.description,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }

        Text(
            text = "Open",
            color = DarkGreen,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
