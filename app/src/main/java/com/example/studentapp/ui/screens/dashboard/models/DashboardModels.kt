package com.example.studentapp.ui.screens.dashboard.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.studentapp.ui.components.StudentBottomNavItem

@Immutable
data class DashboardUiState(
    val studentName: String,
    val studentId: String,
    val stats: List<DashboardStat>,
    val courses: List<CourseSnapshot>,
    val requestStatus: ServiceRequestStatus,
    val navigationItems: List<StudentBottomNavItem>
)

@Immutable
data class DashboardStat(
    val value: String,
    val label: String,
    val icon: ImageVector,
    val isHighlighted: Boolean = false
)

@Immutable
data class CourseSnapshot(
    val code: String,
    val title: String,
    val schedule: String
)

@Immutable
data class ServiceRequestStatus(
    val title: String,
    val reference: String,
    val statusLabel: String,
    val progress: Float,
    val estimatedCompletion: String
)

fun buildDashboardUiState(): DashboardUiState {
    return DashboardUiState(
        studentName = "Alex Harrison",
        studentId = "2024-08912-CS",
        stats = listOf(
            DashboardStat(
                value = "$1,240.00",
                label = "Current Balance",
                icon = Icons.Filled.AccountBalanceWallet,
                isHighlighted = true
            ),
            DashboardStat(
                value = "3.85",
                label = "Cumulative GPA",
                icon = Icons.Filled.Star
            ),
            DashboardStat(
                value = "4 New",
                label = "Notifications",
                icon = Icons.Filled.Notifications
            )
        ),
        courses = listOf(
            CourseSnapshot(
                code = "CS",
                title = "Advanced Algorithms",
                schedule = "Mon, Wed | 09:00 AM"
            ),
            CourseSnapshot(
                code = "UI",
                title = "Human Computer Interaction",
                schedule = "Tue, Thu | 02:30 PM"
            )
        ),
        requestStatus = ServiceRequestStatus(
            title = "Official Transcript Request",
            reference = "#REQ-99021",
            statusLabel = "PROCESSING",
            progress = 0.65f,
            estimatedCompletion = "2 Days"
        ),
        navigationItems = listOf(
            StudentBottomNavItem(
                id = "home",
                label = "Home",
                icon = Icons.Filled.Home
            ),
            StudentBottomNavItem(
                id = "academic",
                label = "Academic",
                icon = Icons.Filled.School
            ),
            StudentBottomNavItem(
                id = "finance",
                label = "Finance",
                icon = Icons.Filled.AccountBalanceWallet
            ),
            StudentBottomNavItem(
                id = "services",
                label = "Services",
                icon = Icons.Filled.Widgets
            ),
            StudentBottomNavItem(
                id = "profile",
                label = "Profile",
                icon = Icons.Filled.Person
            )
        )
    )
}
