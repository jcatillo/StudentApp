package com.example.studentapp.ui.screens.evaluation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.screens.evaluations.EvaluationScreenColors

@Composable
fun EvaluationSubmitButton(
    text: String,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = EvaluationScreenColors.Yellow
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = EvaluationScreenColors.PrimaryGreenDark
        )
    }
}
