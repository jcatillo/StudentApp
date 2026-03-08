package com.example.studentapp.ui.screens.profile.utils

data class ProfileManagementErrors(
    val fullName: String? = null,
    val emailAddress: String? = null,
    val phoneNumber: String? = null
)

data class PasswordFormErrors(
    val currentPassword: String? = null,
    val newPassword: String? = null,
    val confirmPassword: String? = null
)

data class EmergencyContactErrors(
    val name: String? = null,
    val relationship: String? = null,
    val phoneNumber: String? = null
)

fun validateProfileManagementForm(
    fullName: String,
    emailAddress: String,
    phoneNumber: String
): ProfileManagementErrors {
    return ProfileManagementErrors(
        fullName = if (fullName.isBlank()) "Full name is required." else null,
        emailAddress = when {
            emailAddress.isBlank() -> "Email address is required."
            !emailAddress.contains("@") -> "Use a valid email address."
            else -> null
        },
        phoneNumber = if (phoneNumber.isBlank()) "Phone number is required." else null
    )
}

fun validatePasswordForm(
    currentPassword: String,
    newPassword: String,
    confirmPassword: String
): PasswordFormErrors {
    return PasswordFormErrors(
        currentPassword = if (currentPassword.isBlank()) "Current password is required." else null,
        newPassword = when {
            newPassword.isBlank() -> "New password is required."
            newPassword.length < 8 -> "New password must be at least 8 characters."
            else -> null
        },
        confirmPassword = when {
            confirmPassword.isBlank() -> "Confirm your new password."
            confirmPassword != newPassword -> "Password confirmation does not match."
            else -> null
        }
    )
}

fun validateEmergencyContactForm(
    name: String,
    relationship: String,
    phoneNumber: String
): EmergencyContactErrors {
    return EmergencyContactErrors(
        name = if (name.isBlank()) "Contact name is required." else null,
        relationship = if (relationship.isBlank()) "Relationship is required." else null,
        phoneNumber = if (phoneNumber.isBlank()) "Phone number is required." else null
    )
}

fun validateOtpCode(otpCode: String): String? {
    return when {
        otpCode.isBlank() -> "Enter the 6-digit verification code."
        otpCode.length != 6 -> "Verification code must be 6 digits."
        !otpCode.all { it.isDigit() } -> "Verification code must contain digits only."
        else -> null
    }
}
