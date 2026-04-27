package com.example.studentapp.domain.usecase

import com.example.studentapp.domain.model.EmergencyContactInfo
import com.example.studentapp.domain.model.NotificationPreferences
import com.example.studentapp.domain.model.ProfileOverview
import com.example.studentapp.domain.model.TwoFactorStatus
import com.example.studentapp.domain.repository.StudentRepository

class GetProfileOverviewUseCase(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(studentId: String): ProfileOverview? {
        return try {
            val response = repository.getProfile(studentId)
            if (response.isSuccessful) {
                val dto = response.body() ?: return null
                ProfileOverview(
                    accountId = dto.id,
                    fullName = dto.fullName,
                    emailAddress = dto.emailAddress,
                    phoneNumber = dto.phoneNumber,
                    accountLabel = dto.accountLabel,
                    programSummary = dto.programSummary,
                    emergencyContact = EmergencyContactInfo(
                        name = dto.emergencyContactName,
                        relationship = dto.emergencyContactRelationship,
                        phoneNumber = dto.emergencyContactPhoneNumber
                    ),
                    twoFactorStatus = try {
                        TwoFactorStatus.valueOf(dto.twoFactorStatus)
                    } catch (e: Exception) {
                        TwoFactorStatus.Disabled
                    },
                    notificationPreferences = NotificationPreferences(
                        emailNotifications = dto.emailNotifications,
                        smsNotifications = dto.smsNotifications,
                        systemAlerts = dto.systemAlerts
                    )
                )
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}

