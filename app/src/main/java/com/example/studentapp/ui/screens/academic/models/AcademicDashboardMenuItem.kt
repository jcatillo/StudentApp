package com.example.studentapp.ui.screens.academic.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AssignmentTurnedIn
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.PersonAddAlt1
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.School
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
data class AcademicDashboardMenuItem(
    val id: String,
    val label: String,
    val icon: ImageVector
)

const val ACADEMIC_MENU_PROGRAMS = "programs"

fun buildAcademicDashboardMenuItems(): List<AcademicDashboardMenuItem> {
    return listOf(
        AcademicDashboardMenuItem(
            id = ACADEMIC_MENU_PROGRAMS,
            label = "Programs",
            icon = Icons.Outlined.School
        ),
        AcademicDashboardMenuItem(
            id = "courses",
            label = "Courses",
            icon = Icons.Outlined.MenuBook
        ),
        AcademicDashboardMenuItem(
            id = "enrollment",
            label = "Enrollment",
            // Material Icons does not expose `person_add_alt`; `PersonAddAlt1` is the closest official equivalent.
            icon = Icons.Outlined.PersonAddAlt1
        ),
        AcademicDashboardMenuItem(
            id = "grades",
            label = "Grades",
            icon = Icons.Outlined.BarChart
        ),
        AcademicDashboardMenuItem(
            id = "evaluations",
            label = "Evaluations",
            icon = Icons.Outlined.AssignmentTurnedIn
        ),
        AcademicDashboardMenuItem(
            id = "study_load",
            label = "Study Load",
            icon = Icons.Outlined.Schedule
        )
    )
}
