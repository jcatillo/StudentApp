package com.example.studentapp.ui.screens.profile.models

enum class ProfileSectionDestination(
    val title: String,
    val description: String
) {
    ProfileManagement(
        title = "Profile Management",
        description = "Update your personal and contact details."
    ),
    EmergencyContact(
        title = "Emergency Contact",
        description = "Maintain your priority contact information."
    ),
    QrCode(
        title = "Downloadable QR Code",
        description = "Access and export your identity QR code."
    ),
    ChangePassword(
        title = "Change Password",
        description = "Review and update your account password."
    ),
    TwoFactor(
        title = "Two-Factor Authentication",
        description = "Set up OTP protection for your account."
    ),
    NotificationSettings(
        title = "Notification Settings",
        description = "Control how the app sends updates and alerts."
    )
}

fun buildProfileShortcutDestinations(): List<ProfileSectionDestination> {
    return listOf(
        ProfileSectionDestination.ProfileManagement,
        ProfileSectionDestination.EmergencyContact,
        ProfileSectionDestination.QrCode,
        ProfileSectionDestination.ChangePassword,
        ProfileSectionDestination.TwoFactor,
        ProfileSectionDestination.NotificationSettings
    )
}
