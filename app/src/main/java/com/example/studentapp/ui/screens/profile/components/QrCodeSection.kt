package com.example.studentapp.ui.screens.profile.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.profile.utils.createProfileQrBitmap
import com.example.studentapp.ui.theme.DarkGreen

@Composable
fun QrCodeSection(
    accountId: String,
    qrPayload: String,
    feedbackMessage: String?,
    onDownloadQrCode: (Uri) -> Unit,
    modifier: Modifier = Modifier
) {
    val qrBitmap = remember(qrPayload) {
        createProfileQrBitmap(payload = qrPayload)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("image/png")
    ) { uri ->
        if (uri != null) {
            onDownloadQrCode(uri)
        }
    }

    ProfileSectionCard(
        title = "Downloadable QR Code",
        subtitle = "Use this QR code for profile verification and campus identity checks.",
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    bitmap = qrBitmap.asImageBitmap(),
                    contentDescription = "Profile QR code",
                    modifier = Modifier
                        .size(196.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(16.dp)
                )
            }

            Text(
                text = "Linked to $accountId",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Download a PNG copy for check-in workflows or identity validation when you are offline.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            if (feedbackMessage != null) {
                ProfileFeedbackText(message = feedbackMessage)
            }

            Button(
                onClick = {
                    launcher.launch("$accountId-profile-qr.png")
                },
                colors = ButtonDefaults.buttonColors(containerColor = DarkGreen)
            ) {
                Text(
                    text = "Download PNG",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
