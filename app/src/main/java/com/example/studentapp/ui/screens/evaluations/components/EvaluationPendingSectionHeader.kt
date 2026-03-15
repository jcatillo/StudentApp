package com.example.studentapp.ui.screens.evaluations.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.evaluations.EvaluationScreenColors

@Composable
fun EvaluationPendingSectionHeader(
    title: String,
    remainingText: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = EvaluationScreenColors.TextPrimary
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = remainingText,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = EvaluationScreenColors.Yellow,
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(EvaluationScreenColors.YellowSoft.copy(alpha = 0.35f))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}
