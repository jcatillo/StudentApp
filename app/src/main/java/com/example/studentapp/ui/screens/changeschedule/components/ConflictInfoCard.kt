package com.example.studentapp.ui.screens.changeschedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.screens.changeschedule.ChangeScheduleColors

@Composable
fun ConflictInfoCard(
    text: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                ChangeScheduleColors.InfoBackground,
                RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
    ) {

        Icon(Icons.Outlined.Info, null)

        Spacer(Modifier.width(8.dp))

        Text(text)

    }

}
