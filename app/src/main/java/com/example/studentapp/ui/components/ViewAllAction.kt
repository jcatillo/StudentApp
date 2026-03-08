package com.example.studentapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.theme.DarkGreen
import com.example.studentapp.ui.theme.PrimaryTint

@Composable
fun ViewAllAction(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "View All"
) {
    Box(
        modifier = modifier
            .background(
                color = PrimaryTint,
                shape = RoundedCornerShape(999.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            color = DarkGreen,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
