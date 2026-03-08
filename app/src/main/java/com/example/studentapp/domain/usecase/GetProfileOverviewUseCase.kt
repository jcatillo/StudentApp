package com.example.studentapp.domain.usecase

import com.example.studentapp.domain.model.EmergencyContactInfo
import com.example.studentapp.domain.model.NotificationPreferences
import com.example.studentapp.domain.model.ProfileOverview
import com.example.studentapp.domain.model.TwoFactorStatus

class GetProfileOverviewUseCase {
    operator fun invoke(): ProfileOverview {
        return ProfileOverview(
            accountId = "STU-2024-08912-CS",
            fullName = "Alex Harrison",
            emailAddress = "alex.harrison@studentapp.edu",
            phoneNumber = "+63 917 555 2048",
            accountLabel = "Student Portal Account",
            programSummary = "Computer Science • Year 3",
            emergencyContact = EmergencyContactInfo(
                name = "Maria Harrison",
                relationship = "Mother",
                phoneNumber = "+63 917 555 3391"
            ),
            twoFactorStatus = TwoFactorStatus.Disabled,
            notificationPreferences = NotificationPreferences(
                emailNotifications = true,
                smsNotifications = false,
                systemAlerts = true
            )
        )
    }
}
