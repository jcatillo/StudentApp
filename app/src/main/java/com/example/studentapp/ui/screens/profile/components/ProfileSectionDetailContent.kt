package com.example.studentapp.ui.screens.profile.components

import android.net.Uri
import androidx.compose.runtime.Composable
import com.example.studentapp.ui.screens.profile.models.ProfileSectionDestination
import com.example.studentapp.ui.screens.profile.models.ProfileUiState
import com.example.studentapp.ui.screens.profile.state.ProfileScreenState

@Composable
fun ProfileSectionDetailContent(
    destination: ProfileSectionDestination,
    state: ProfileUiState,
    screenState: ProfileScreenState,
    onDownloadQrCode: (Uri) -> Unit
) {
    when (destination) {
        ProfileSectionDestination.ProfileManagement -> {
            ProfileManagementSection(
                fullName = screenState.profileForm.fullName,
                emailAddress = screenState.profileForm.emailAddress,
                phoneNumber = screenState.profileForm.phoneNumber,
                accountLabel = state.accountLabel,
                isEditing = screenState.isEditingProfile,
                errors = screenState.profileErrors,
                feedbackMessage = screenState.profileFeedbackMessage,
                onEditClick = screenState::startProfileEditing,
                onCancelClick = {
                    screenState.cancelProfileEditing(state)
                },
                onSaveClick = screenState::saveProfile,
                onFullNameChange = screenState::updateFullName,
                onEmailAddressChange = screenState::updateEmailAddress,
                onPhoneNumberChange = screenState::updatePhoneNumber
            )
        }

        ProfileSectionDestination.EmergencyContact -> {
            EmergencyContactSection(
                contactName = screenState.emergencyContactForm.name,
                relationship = screenState.emergencyContactForm.relationship,
                phoneNumber = screenState.emergencyContactForm.phoneNumber,
                isEditing = screenState.isEditingEmergencyContact,
                errors = screenState.emergencyContactErrors,
                feedbackMessage = screenState.emergencyContactFeedbackMessage,
                onEditClick = screenState::startEmergencyContactEditing,
                onCancelClick = {
                    screenState.cancelEmergencyContactEditing(state)
                },
                onSaveClick = screenState::saveEmergencyContact,
                onNameChange = screenState::updateEmergencyContactName,
                onRelationshipChange = screenState::updateEmergencyContactRelationship,
                onPhoneNumberChange = screenState::updateEmergencyContactPhone
            )
        }

        ProfileSectionDestination.QrCode -> {
            QrCodeSection(
                accountId = state.accountId,
                qrPayload = state.qrPayload,
                feedbackMessage = screenState.qrFeedbackMessage,
                onDownloadQrCode = onDownloadQrCode
            )
        }

        ProfileSectionDestination.ChangePassword -> {
            ChangePasswordSection(
                formState = screenState.passwordForm,
                errors = screenState.passwordErrors,
                feedbackMessage = screenState.passwordFeedbackMessage,
                isCurrentPasswordVisible = screenState.isCurrentPasswordVisible,
                isNewPasswordVisible = screenState.isNewPasswordVisible,
                isConfirmPasswordVisible = screenState.isConfirmPasswordVisible,
                onCurrentPasswordChange = screenState::updateCurrentPassword,
                onNewPasswordChange = screenState::updateNewPassword,
                onConfirmPasswordChange = screenState::updateConfirmPassword,
                onCurrentPasswordVisibilityToggle = screenState::toggleCurrentPasswordVisibility,
                onNewPasswordVisibilityToggle = screenState::toggleNewPasswordVisibility,
                onConfirmPasswordVisibilityToggle = screenState::toggleConfirmPasswordVisibility,
                onSavePasswordClick = screenState::savePassword
            )
        }

        ProfileSectionDestination.TwoFactor -> {
            TwoFactorSection(
                status = screenState.twoFactorStatus,
                otpCode = screenState.otpCode,
                otpErrorMessage = screenState.otpErrorMessage,
                feedbackMessage = screenState.securityFeedbackMessage,
                isSetupExpanded = screenState.isTwoFactorSetupExpanded,
                onTwoFactorEnabledChange = screenState::toggleTwoFactorEnabled,
                onOtpCodeChange = screenState::updateOtpCode,
                onVerifyClick = screenState::verifyTwoFactorCode,
                onCancelSetupClick = screenState::cancelTwoFactorSetup
            )
        }

        ProfileSectionDestination.NotificationSettings -> {
            NotificationSettingsSection(
                settings = screenState.notificationSettings,
                onEmailNotificationsChange = screenState::setEmailNotifications,
                onSmsNotificationsChange = screenState::setSmsNotifications,
                onSystemAlertsChange = screenState::setSystemAlerts
            )
        }
    }
}
