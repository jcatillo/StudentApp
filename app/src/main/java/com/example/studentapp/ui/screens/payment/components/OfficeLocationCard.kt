package com.example.studentapp.ui.screens.payment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun OfficeLocationCard() {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
        Text(
            text = "OFFICE LOCATION",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuCmipYdAnktr5V4E8vPJv7aVO1fxoSR4O2Ejj9m84K_sN7WtuujcRdwh6KfH4iK-Iyr9rxKTxCNL8Nb1Pwb85dvkwMg6fXFL6fBZOfZqCV93B2wXI0qOhrhEQgBFAWUxtT7n9dMqu84tF2k-2hDz2oITyMPB_nERw9SHUC9Dfxz_1BSi3aZLk8YQ-1HUw8IDslnPhzAnl_8Ll9fHhbLiHUKIIqzdDz5pZzWsfyAoDn65u4oSUbXzuFfXk8zucmrQdeX5JI7tSeMp_8",
                contentDescription = "Office Location Map",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.8f
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.6f)
                            )
                        )
                    )
            )

            Text(
                text = "Student Services Center, 2nd Floor",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            )
        }
    }
}
