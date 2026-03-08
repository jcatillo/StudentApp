package com.example.studentapp.ui.screens.profile.state

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.studentapp.domain.model.TwoFactorStatus
import com.example.studentapp.domain.usecase.ExportProfileQrCodeUseCase
import com.example.studentapp.ui.screens.profile.models.NotificationSettingsUiState
import com.example.studentapp.ui.screens.profile.models.ProfileUiState
import com.example.studentapp.ui.screens.profile.utils.EmergencyContactErrors
import com.example.studentapp.ui.screens.profile.utils.PasswordFormErrors
import com.example.studentapp.ui.screens.profile.utils.ProfileManagementErrors
import com.example.studentapp.ui.screens.profile.utils.validateEmergencyContactForm
import com.example.studentapp.ui.screens.profile.utils.validateOtpCode
import com.example.studentapp.ui.screens.profile.utils.validatePasswordForm
import com.example.studentapp.ui.screens.profile.utils.validateProfileManagementForm

data class ProfileFormState(
    val fullName: String,
    val emailAddress: String,
    val phoneNumber: String
)

data class PasswordFormState(
    val currentPassword: String = "",
    val newPassword: String = "",
    val confirmPassword: String = ""
)

data class EmergencyContactFormState(
    val name: String,
    val relationship: String,
    val phoneNumber: String
)

@Stable
class ProfileScreenState internal constructor(
    initialState: ProfileUiState,
    private val exportProfileQrCodeUseCase: ExportProfileQrCodeUseCase
) {
    var profileForm by mutableStateOf(
        ProfileFormState(
            fullName = initialState.fullName,
            emailAddress = initialState.emailAddress,
            phoneNumber = initialState.phoneNumber
        )
    )
        private set

    var passwordForm by mutableStateOf(PasswordFormState())
        private set

    var emergencyContactForm by mutableStateOf(
        EmergencyContactFormState(
            name = initialState.emergencyContact.name,
            relationship = initialState.emergencyContact.relationship,
            phoneNumber = initialState.emergencyContact.phoneNumber
        )
    )
        private set

    var notificationSettings by mutableStateOf(initialState.notificationSettings)
        private set

    var twoFactorStatus by mutableStateOf(initialState.twoFactorStatus)
        private set

    var otpCode by mutableStateOf("")
        private set

    var isEditingProfile by mutableStateOf(false)
        private set

    var isEditingEmergencyContact by mutableStateOf(false)
        private set

    var isTwoFactorSetupExpanded by mutableStateOf(initialState.twoFactorStatus == TwoFactorStatus.PendingVerification)
        private set

    var isNewPasswordVisible by mutableStateOf(false)
        private set

    var isConfirmPasswordVisible by mutableStateOf(false)
        private set

    var isCurrentPasswordVisible by mutableStateOf(false)
        private set

    var profileErrors by mutableStateOf(ProfileManagementErrors())
        private set

    var passwordErrors by mutableStateOf(PasswordFormErrors())
        private set

    var emergencyContactErrors by mutableStateOf(EmergencyContactErrors())
        private set

    var otpErrorMessage by mutableStateOf<String?>(null)
        private set

    var profileFeedbackMessage by mutableStateOf<String?>(null)
        private set

    var passwordFeedbackMessage by mutableStateOf<String?>(null)
        private set

    var securityFeedbackMessage by mutableStateOf<String?>(null)
        private set

    var emergencyContactFeedbackMessage by mutableStateOf<String?>(null)
        private set

    var qrFeedbackMessage by mutableStateOf<String?>(null)
        private set

    fun startProfileEditing() {
        isEditingProfile = true
        profileFeedbackMessage = null
    }

    fun cancelProfileEditing(initialState: ProfileUiState) {
        profileForm = ProfileFormState(
            fullName = initialState.fullName,
            emailAddress = initialState.emailAddress,
            phoneNumber = initialState.phoneNumber
        )
        profileErrors = ProfileManagementErrors()
        profileFeedbackMessage = null
        isEditingProfile = false
    }

    fun updateFullName(value: String) {
        profileForm = profileForm.copy(fullName = value)
        profileErrors = profileErrors.copy(fullName = null)
        profileFeedbackMessage = null
    }

    fun updateEmailAddress(value: String) {
        profileForm = profileForm.copy(emailAddress = value)
        profileErrors = profileErrors.copy(emailAddress = null)
        profileFeedbackMessage = null
    }

    fun updatePhoneNumber(value: String) {
        profileForm = profileForm.copy(phoneNumber = value)
        profileErrors = profileErrors.copy(phoneNumber = null)
        profileFeedbackMessage = null
    }

    fun saveProfile() {
        val errors = validateProfileManagementForm(
            fullName = profileForm.fullName,
            emailAddress = profileForm.emailAddress,
            phoneNumber = profileForm.phoneNumber
        )

        profileErrors = errors

        if (errors.fullName != null || errors.emailAddress != null || errors.phoneNumber != null) {
            profileFeedbackMessage = null
            return
        }

        isEditingProfile = false
        profileFeedbackMessage = "Profile changes saved successfully."
    }

    fun updateCurrentPassword(value: String) {
        passwordForm = passwordForm.copy(currentPassword = value)
        passwordErrors = passwordErrors.copy(currentPassword = null)
        passwordFeedbackMessage = null
    }

    fun updateNewPassword(value: String) {
        passwordForm = passwordForm.copy(newPassword = value)
        passwordErrors = passwordErrors.copy(newPassword = null)
        passwordFeedbackMessage = null
    }

    fun updateConfirmPassword(value: String) {
        passwordForm = passwordForm.copy(confirmPassword = value)
        passwordErrors = passwordErrors.copy(confirmPassword = null)
        passwordFeedbackMessage = null
    }

    fun toggleCurrentPasswordVisibility() {
        isCurrentPasswordVisible = !isCurrentPasswordVisible
    }

    fun toggleNewPasswordVisibility() {
        isNewPasswordVisible = !isNewPasswordVisible
    }

    fun toggleConfirmPasswordVisibility() {
        isConfirmPasswordVisible = !isConfirmPasswordVisible
    }

    fun savePassword() {
        val errors = validatePasswordForm(
            currentPassword = passwordForm.currentPassword,
            newPassword = passwordForm.newPassword,
            confirmPassword = passwordForm.confirmPassword
        )

        passwordErrors = errors

        if (
            errors.currentPassword != null ||
            errors.newPassword != null ||
            errors.confirmPassword != null
        ) {
            passwordFeedbackMessage = null
            return
        }

        passwordForm = PasswordFormState()
        passwordFeedbackMessage = "Password updated successfully."
    }

    fun toggleTwoFactorEnabled(enabled: Boolean) {
        if (enabled) {
            twoFactorStatus = TwoFactorStatus.PendingVerification
            isTwoFactorSetupExpanded = true
            securityFeedbackMessage = "Enter the OTP code to finish setup."
        } else {
            twoFactorStatus = TwoFactorStatus.Disabled
            isTwoFactorSetupExpanded = false
            otpCode = ""
            otpErrorMessage = null
            securityFeedbackMessage = "Two-factor authentication has been disabled."
        }
    }

    fun updateOtpCode(value: String) {
        otpCode = value
        otpErrorMessage = null
        securityFeedbackMessage = null
    }

    fun verifyTwoFactorCode() {
        val error = validateOtpCode(otpCode)

        if (error != null) {
            otpErrorMessage = error
            securityFeedbackMessage = null
            return
        }

        twoFactorStatus = TwoFactorStatus.Enabled
        isTwoFactorSetupExpanded = false
        otpCode = ""
        otpErrorMessage = null
        securityFeedbackMessage = "Two-factor authentication is now enabled."
    }

    fun cancelTwoFactorSetup() {
        twoFactorStatus = TwoFactorStatus.Disabled
        isTwoFactorSetupExpanded = false
        otpCode = ""
        otpErrorMessage = null
        securityFeedbackMessage = null
    }

    fun startEmergencyContactEditing() {
        isEditingEmergencyContact = true
        emergencyContactFeedbackMessage = null
    }

    fun cancelEmergencyContactEditing(initialState: ProfileUiState) {
        emergencyContactForm = EmergencyContactFormState(
            name = initialState.emergencyContact.name,
            relationship = initialState.emergencyContact.relationship,
            phoneNumber = initialState.emergencyContact.phoneNumber
        )
        emergencyContactErrors = EmergencyContactErrors()
        emergencyContactFeedbackMessage = null
        isEditingEmergencyContact = false
    }

    fun updateEmergencyContactName(value: String) {
        emergencyContactForm = emergencyContactForm.copy(name = value)
        emergencyContactErrors = emergencyContactErrors.copy(name = null)
        emergencyContactFeedbackMessage = null
    }

    fun updateEmergencyContactRelationship(value: String) {
        emergencyContactForm = emergencyContactForm.copy(relationship = value)
        emergencyContactErrors = emergencyContactErrors.copy(relationship = null)
        emergencyContactFeedbackMessage = null
    }

    fun updateEmergencyContactPhone(value: String) {
        emergencyContactForm = emergencyContactForm.copy(phoneNumber = value)
        emergencyContactErrors = emergencyContactErrors.copy(phoneNumber = null)
        emergencyContactFeedbackMessage = null
    }

    fun saveEmergencyContact() {
        val errors = validateEmergencyContactForm(
            name = emergencyContactForm.name,
            relationship = emergencyContactForm.relationship,
            phoneNumber = emergencyContactForm.phoneNumber
        )

        emergencyContactErrors = errors

        if (errors.name != null || errors.relationship != null || errors.phoneNumber != null) {
            emergencyContactFeedbackMessage = null
            return
        }

        isEditingEmergencyContact = false
        emergencyContactFeedbackMessage = "Emergency contact saved successfully."
    }

    fun setEmailNotifications(enabled: Boolean) {
        notificationSettings = notificationSettings.copy(emailNotifications = enabled)
    }

    fun setSmsNotifications(enabled: Boolean) {
        notificationSettings = notificationSettings.copy(smsNotifications = enabled)
    }

    fun setSystemAlerts(enabled: Boolean) {
        notificationSettings = notificationSettings.copy(systemAlerts = enabled)
    }

    fun downloadQrCode(
        context: Context,
        outputUri: Uri,
        payload: String
    ) {
        val result = exportProfileQrCodeUseCase(
            context = context,
            outputUri = outputUri,
            payload = payload
        )

        qrFeedbackMessage = result.message
    }
}

@Composable
fun rememberProfileScreenState(
    initialState: ProfileUiState
): ProfileScreenState {
    val exportProfileQrCodeUseCase = remember {
        ExportProfileQrCodeUseCase()
    }

    return remember(initialState) {
        ProfileScreenState(
            initialState = initialState,
            exportProfileQrCodeUseCase = exportProfileQrCodeUseCase
        )
    }
}
