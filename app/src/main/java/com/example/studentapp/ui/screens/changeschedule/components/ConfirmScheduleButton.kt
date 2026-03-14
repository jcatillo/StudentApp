package com.example.studentapp.ui.screens.changeschedule.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.screens.changeschedule.ChangeScheduleColors

@Composable
fun ConfirmScheduleButton(
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ChangeScheduleColors.PrimaryGreen
        )
    ) {

        Text("Confirm Schedule Change")

    }

}
