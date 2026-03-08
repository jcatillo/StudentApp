package com.example.studentapp.ui.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.domain.model.TwoFactorStatus
import com.example.studentapp.ui.theme.DarkGreen
import com.example.studentapp.ui.theme.Gold

@Composable
fun TwoFactorSection(
    status: TwoFactorStatus,
    otpCode: String,
    otpErrorMessage: String?,
    feedbackMessage: String?,
    isSetupExpanded: Boolean,
    onTwoFactorEnabledChange: (Boolean) -> Unit,
    onOtpCodeChange: (String) -> Unit,
    onVerifyClick: () -> Unit,
    onCancelSetupClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val badgeSpec = twoFactorBadgeSpec(status)

    ProfileSectionCard(
        title = "Two-Factor Authentication",
        subtitle = "Add a one-time verification step before anyone can access your account.",
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Authenticator app verification",
                        color = Color(0xFF0F172A),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    ProfileStatusBadge(
                        label = badgeSpec.label,
                        containerColor = badgeSpec.containerColor,
                        contentColor = badgeSpec.contentColor
                    )
                }

                Switch(
                    checked = status != TwoFactorStatus.Disabled,
                    onCheckedChange = onTwoFactorEnabledChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = DarkGreen,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color(0xFFCBD5E1)
                    )
                )
            }

            Text(
                text = buildStatusMessage(status),
                color = Color(0xFF475569),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            if (isSetupExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFF8FAF8),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "Enter the 6-digit code from your authenticator app to finish setup.",
                        color = Color(0xFF0F172A),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 20.sp
                    )

                    ProfileTextField(
                        label = "Verification Code",
                        value = otpCode,
                        onValueChange = onOtpCodeChange,
                        errorMessage = otpErrorMessage,
                        placeholder = "000000",
                        keyboardType = KeyboardType.NumberPassword
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = onCancelSetupClick,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkGreen)
                        ) {
                            Text(text = "Cancel")
                        }

                        Button(
                            onClick = onVerifyClick,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Gold,
                                contentColor = Color(0xFF1F2937)
                            )
                        ) {
                            Text(
                                text = "Verify OTP",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            if (feedbackMessage != null) {
                ProfileFeedbackText(message = feedbackMessage)
            }
        }
    }
}

private fun buildStatusMessage(status: TwoFactorStatus): String {
    return when (status) {
        TwoFactorStatus.Disabled -> "Turn this on to require an OTP code every time you sign in."
        TwoFactorStatus.PendingVerification -> "Setup is in progress. Verify the OTP code to activate protection."
        TwoFactorStatus.Enabled -> "Your account now requires an OTP code in addition to your password."
    }
}

private fun twoFactorBadgeSpec(status: TwoFactorStatus): TwoFactorBadgeSpec {
    return when (status) {
        TwoFactorStatus.Disabled -> {
            TwoFactorBadgeSpec(
                label = "Disabled",
                containerColor = Color(0xFFF1F5F9),
                contentColor = Color(0xFF475569)
            )
        }

        TwoFactorStatus.PendingVerification -> {
            TwoFactorBadgeSpec(
                label = "Pending",
                containerColor = Gold.copy(alpha = 0.18f),
                contentColor = Color(0xFF8A5A00)
            )
        }

        TwoFactorStatus.Enabled -> {
            TwoFactorBadgeSpec(
                label = "Enabled",
                containerColor = DarkGreen.copy(alpha = 0.16f),
                contentColor = DarkGreen
            )
        }
    }
}

private data class TwoFactorBadgeSpec(
    val label: String,
    val containerColor: Color,
    val contentColor: Color
)
