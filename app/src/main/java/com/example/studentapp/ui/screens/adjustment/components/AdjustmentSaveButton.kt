package com.example.studentapp.ui.screens.adjustment.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.screens.adjustment.AdjustmentScreenColors

@Composable
fun AdjustmentSaveButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AdjustmentScreenColors.Yellow
        )
    ) {
        Icon(
            imageVector = Icons.Outlined.Save,
            contentDescription = null
        )
        androidx.compose.foundation.layout.Spacer(
            modifier = Modifier.height(0.dp)
        )
        Text(
            text = "  $text",
            fontWeight = FontWeight.Bold
        )
    }
}
