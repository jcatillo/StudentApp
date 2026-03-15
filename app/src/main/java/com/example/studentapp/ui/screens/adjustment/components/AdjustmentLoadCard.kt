package com.example.studentapp.ui.screens.adjustment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.adjustment.AdjustmentScreenColors

@Composable
fun AdjustmentLoadCard(
    currentLoad: Int,
    maxLoad: Int
) {
    val progress = (currentLoad.toFloat() / maxLoad.toFloat()).coerceIn(0f, 1f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Current Load",
            fontSize = 15.sp,
            color = AdjustmentScreenColors.TextSecondary,
            fontWeight = FontWeight.Medium
        )

        androidx.compose.foundation.layout.Row {
            Text(
                text = currentLoad.toString(),
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = AdjustmentScreenColors.TextPrimary
            )
            Text(
                text = " Units",
                fontSize = 16.sp,
                color = AdjustmentScreenColors.TextSecondary,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(50)),
            color = AdjustmentScreenColors.ProgressFill,
            trackColor = AdjustmentScreenColors.ProgressTrack
        )
    }
}
