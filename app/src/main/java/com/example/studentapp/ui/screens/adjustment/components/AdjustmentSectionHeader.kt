package com.example.studentapp.ui.screens.adjustment.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.adjustment.AdjustmentScreenColors

@Composable
fun AdjustmentSectionHeader(
    title: String,
    addMode: Boolean = false
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = if (addMode) Icons.Outlined.AddCircle else Icons.Outlined.CheckCircle,
            contentDescription = null,
            tint = AdjustmentScreenColors.PrimaryGreen
        )
        Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = AdjustmentScreenColors.TextPrimary
        )
    }
}
