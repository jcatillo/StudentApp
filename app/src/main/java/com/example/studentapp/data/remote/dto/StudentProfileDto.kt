package com.example.studentapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class StudentProfileDto(
    val id: String,
    val fullName: String,
    val emailAddress: String,
    val phoneNumber: String,
    val accountLabel: String,
    val programSummary: String,
    val twoFactorStatus: String,
    val emergencyContactName: String,
    val emergencyContactRelationship: String,
    val emergencyContactPhoneNumber: String,
    val emailNotifications: Boolean,
    val smsNotifications: Boolean,
    val systemAlerts: Boolean
)
