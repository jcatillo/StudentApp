@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.studentapp.ui.screens.adjustment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.adjustment.AdjustmentScreenColors

@Composable
fun AdjustmentTopBar(
    title: String,
    semesterLabel: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = AdjustmentScreenColors.TextPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = AdjustmentScreenColors.TextPrimary
                )
            }
        },
        actions = {
            Row(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clip(RoundedCornerShape(50))
                    .background(AdjustmentScreenColors.GreenSoft)
                    .padding(horizontal = 14.dp, vertical = 7.dp)
            ) {
                Text(
                    text = semesterLabel,
                    color = AdjustmentScreenColors.PrimaryGreen,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AdjustmentScreenColors.Background
        )
    )
}
