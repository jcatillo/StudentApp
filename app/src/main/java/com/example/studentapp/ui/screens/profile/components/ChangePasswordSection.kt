package com.example.studentapp.ui.screens.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.profile.state.PasswordFormState
import com.example.studentapp.ui.screens.profile.utils.PasswordFormErrors
import com.example.studentapp.ui.theme.DarkGreen

@Composable
fun ChangePasswordSection(
    formState: PasswordFormState,
    errors: PasswordFormErrors,
    feedbackMessage: String?,
    isCurrentPasswordVisible: Boolean,
    isNewPasswordVisible: Boolean,
    isConfirmPasswordVisible: Boolean,
    onCurrentPasswordChange: (String) -> Unit,
    onNewPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onCurrentPasswordVisibilityToggle: () -> Unit,
    onNewPasswordVisibilityToggle: () -> Unit,
    onConfirmPasswordVisibilityToggle: () -> Unit,
    onSavePasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ProfileSectionCard(
        title = "Change Password",
        subtitle = "Keep your account secure with a strong password that only you know.",
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            ProfileTextField(
                label = "Current Password",
                value = formState.currentPassword,
                onValueChange = onCurrentPasswordChange,
                errorMessage = errors.currentPassword,
                placeholder = "Enter your current password",
                isSecureField = true,
                isValueVisible = isCurrentPasswordVisible,
                onVisibilityToggle = onCurrentPasswordVisibilityToggle,
                keyboardType = KeyboardType.Password
            )

            ProfileTextField(
                label = "New Password",
                value = formState.newPassword,
                onValueChange = onNewPasswordChange,
                errorMessage = errors.newPassword,
                placeholder = "Create a new password",
                isSecureField = true,
                isValueVisible = isNewPasswordVisible,
                onVisibilityToggle = onNewPasswordVisibilityToggle,
                keyboardType = KeyboardType.Password
            )

            ProfileTextField(
                label = "Confirm New Password",
                value = formState.confirmPassword,
                onValueChange = onConfirmPasswordChange,
                errorMessage = errors.confirmPassword,
                placeholder = "Repeat your new password",
                isSecureField = true,
                isValueVisible = isConfirmPasswordVisible,
                onVisibilityToggle = onConfirmPasswordVisibilityToggle,
                keyboardType = KeyboardType.Password
            )

            Text(
                text = "Use at least 8 characters with a mix of letters and numbers.",
                color = Color(0xFF475569),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            if (feedbackMessage != null) {
                ProfileFeedbackText(message = feedbackMessage)
            }

            Button(
                onClick = onSavePasswordClick,
                colors = ButtonDefaults.buttonColors(containerColor = DarkGreen)
            ) {
                Text(
                    text = "Update Password",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
