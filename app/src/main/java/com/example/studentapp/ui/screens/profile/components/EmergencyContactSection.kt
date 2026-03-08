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
import com.example.studentapp.ui.screens.profile.utils.EmergencyContactErrors
import com.example.studentapp.ui.theme.DarkGreen
import com.example.studentapp.ui.theme.Gold

@Composable
fun EmergencyContactSection(
    contactName: String,
    relationship: String,
    phoneNumber: String,
    isEditing: Boolean,
    errors: EmergencyContactErrors,
    feedbackMessage: String?,
    onEditClick: () -> Unit,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onRelationshipChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ProfileSectionCard(
        title = "Emergency Contact",
        subtitle = "Keep one trusted contact available for urgent school or safety-related communication.",
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
                    label = "Contact Name",
                    value = contactName,
                    onValueChange = onNameChange,
                    errorMessage = errors.name,
                    placeholder = "Enter contact name"
                )

                ProfileTextField(
                    label = "Relationship",
                    value = relationship,
                    onValueChange = onRelationshipChange,
                    errorMessage = errors.relationship,
                    placeholder = "Parent, sibling, guardian"
                )

                ProfileTextField(
                    label = "Phone Number",
                    value = phoneNumber,
                    onValueChange = onPhoneNumberChange,
                    errorMessage = errors.phoneNumber,
                    keyboardType = KeyboardType.Phone,
                    placeholder = "+63 900 000 0000"
                )

                if (feedbackMessage != null) {
                    ProfileFeedbackText(message = feedbackMessage)
                }

                EmergencyContactActions(
                    onCancelClick = onCancelClick,
                    onSaveClick = onSaveClick
                )
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileReadOnlyField(
                    label = "Contact Name",
                    value = contactName
                )

                ProfileReadOnlyField(
                    label = "Relationship",
                    value = relationship
                )

                ProfileReadOnlyField(
                    label = "Phone Number",
                    value = phoneNumber
                )

                if (feedbackMessage != null) {
                    ProfileFeedbackText(message = feedbackMessage)
                }
            }
        }
    }
}

@Composable
private fun EmergencyContactActions(
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
                text = "Save Contact",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
