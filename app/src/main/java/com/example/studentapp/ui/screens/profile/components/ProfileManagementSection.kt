package com.example.studentapp.ui.screens.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.profile.utils.ProfileManagementErrors
import com.example.studentapp.ui.theme.DarkGreen
import com.example.studentapp.ui.theme.Gold
import androidx.compose.material3.MaterialTheme

@Composable
fun ProfileManagementSection(
    fullName: String,
    emailAddress: String,
    phoneNumber: String,
    accountLabel: String,
    isEditing: Boolean,
    errors: ProfileManagementErrors,
    feedbackMessage: String?,
    onEditClick: () -> Unit,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    onFullNameChange: (String) -> Unit,
    onEmailAddressChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ProfileSectionCard(
        title = "Profile Management",
        subtitle = "Review your core account details and keep your contact information current.",
        modifier = modifier,
        action = {
            if (!isEditing) {
                ProfileInlineAction(
                    label = "Edit",
                    onClick = onEditClick
                )
            }
        }
    ) {
        if (isEditing) {
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                ProfileTextField(
                    label = "Full Name",
                    value = fullName,
                    onValueChange = onFullNameChange,
                    errorMessage = errors.fullName,
                    placeholder = "Enter your full name"
                )

                ProfileTextField(
                    label = "Email Address",
                    value = emailAddress,
                    onValueChange = onEmailAddressChange,
                    errorMessage = errors.emailAddress,
                    keyboardType = KeyboardType.Email,
                    placeholder = "name@school.edu"
                )

                ProfileTextField(
                    label = "Phone Number",
                    value = phoneNumber,
                    onValueChange = onPhoneNumberChange,
                    errorMessage = errors.phoneNumber,
                    keyboardType = KeyboardType.Phone,
                    placeholder = "+63 900 000 0000"
                )

                Text(
                    text = "Account label: $accountLabel",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp
                )

                if (feedbackMessage != null) {
                    ProfileFeedbackText(message = feedbackMessage)
                }

                ProfileManagementActions(
                    onCancelClick = onCancelClick,
                    onSaveClick = onSaveClick
                )
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileReadOnlyField(
                    label = "Full Name",
                    value = fullName
                )

                ProfileReadOnlyField(
                    label = "Email Address",
                    value = emailAddress
                )

                ProfileReadOnlyField(
                    label = "Phone Number",
                    value = phoneNumber
                )

                ProfileReadOnlyField(
                    label = "Account Label",
                    value = accountLabel
                )

                if (feedbackMessage != null) {
                    ProfileFeedbackText(message = feedbackMessage)
                }
            }
        }
    }
}

@Composable
private fun ProfileManagementActions(
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedButton(
            onClick = onCancelClick,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkGreen)
        ) {
            Text(text = "Cancel")
        }

        Button(
            onClick = onSaveClick,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Gold,
                contentColor = Color(0xFF1F2937)
            )
        ) {
            Text(
                text = "Save Changes",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
