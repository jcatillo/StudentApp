@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.studentapp.ui.screens.changeschedule.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.studentapp.ui.screens.changeschedule.ChangeScheduleColors

@Composable
fun ChangeScheduleTopBar(
    onBack: () -> Unit
) {

    TopAppBar(
        title = {
            Text(
                "Change Schedule",
                fontWeight = FontWeight.Bold,
                color = ChangeScheduleColors.TextPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ChangeScheduleColors.Background
        )
    )
}
