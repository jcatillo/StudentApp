package com.example.studentapp.ui.screens.changeschedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.example.studentapp.ui.screens.changeschedule.ChangeScheduleColors

@Composable
fun CurrentSelectionCard(
    title: String,
    subtitle: String,
    status: String
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = ChangeScheduleColors.Card
        )
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {

            Icon(
                Icons.Outlined.Computer,
                contentDescription = null,
                tint = ChangeScheduleColors.PrimaryGreen,
                modifier = Modifier.size(40.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column(
                Modifier.weight(1f)
            ) {

                Text(
                    title,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    subtitle,
                    color = ChangeScheduleColors.TextSecondary
                )

            }

            Text(
                status,
                color = ChangeScheduleColors.Available,
                fontWeight = FontWeight.Bold
            )

        }

    }
}
