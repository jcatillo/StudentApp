package com.example.studentapp.ui.screens.profile.models

import com.example.studentapp.domain.model.ProfileOverview
import com.example.studentapp.domain.model.TwoFactorStatus

data class ProfileUiState(
    val accountId: String,
    val fullName: String,
    val emailAddress: String,
    val phoneNumber: String,
    val accountLabel: String,
    val programSummary: String,
    val avatarInitials: String,
    val emergencyContact: EmergencyContactUiState,
    val twoFactorStatus: TwoFactorStatus,
    val notificationSettings: NotificationSettingsUiState,
    val qrPayload: String
)

data class EmergencyContactUiState(
    val name: String,
    val relationship: String,
    val phoneNumber: String
)

data class NotificationSettingsUiState(
    val emailNotifications: Boolean,
    val smsNotifications: Boolean,
    val systemAlerts: Boolean
)

fun ProfileOverview.toUiState(): ProfileUiState {
    return ProfileUiState(
        accountId = accountId,
        fullName = fullName,
        emailAddress = emailAddress,
        phoneNumber = phoneNumber,
        accountLabel = accountLabel,
        programSummary = programSummary,
        avatarInitials = buildProfileAvatarInitials(fullName),
        emergencyContact = EmergencyContactUiState(
            name = emergencyContact.name,
            relationship = emergencyContact.relationship,
            phoneNumber = emergencyContact.phoneNumber
        ),
        twoFactorStatus = twoFactorStatus,
        notificationSettings = NotificationSettingsUiState(
            emailNotifications = notificationPreferences.emailNotifications,
            smsNotifications = notificationPreferences.smsNotifications,
            systemAlerts = notificationPreferences.systemAlerts
        ),
        qrPayload = accountId
    )
}

fun buildProfileAvatarInitials(fullName: String): String {
    return fullName
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .joinToString(separator = "") { part ->
            part.first().uppercase()
        }
}
