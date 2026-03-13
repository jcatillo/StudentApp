package com.example.studentapp.ui.screens.academic.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.academic.AcademicScreenColors

@Composable
fun AcademicSupportSection(
    onContactSupportClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
        color = AcademicScreenColors.Primary.copy(alpha = 0.05f),
        border = BorderStroke(1.dp, AcademicScreenColors.Primary.copy(alpha = 0.10f))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Need Assistance?",
                    color = AcademicScreenColors.Slate900,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Contact the academic office for enrollment support or technical issues with your student account.",
                    modifier = Modifier.padding(top = 8.dp),
                    color = AcademicScreenColors.Slate600,
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center
                )
            }

            AcademicPrimaryActionButton(
                text = "Contact Support",
                onClick = onContactSupportClick
            )
        }
    }
}
