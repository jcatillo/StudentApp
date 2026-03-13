package com.example.studentapp.ui.screens.enrollment.models

import androidx.compose.runtime.Immutable

@Immutable
data class EnrollableCourse(
    val code: String,
    val title: String,
    val instructorSchedule: String,
    val units: Int,
    val tuition: Double,
    val isInitiallySelected: Boolean,
    val isLocked: Boolean = false,
    val lockReason: String? = null
)

enum class EnrollmentStep(
    val stepNumber: Int,
    val title: String,
    val progressLabel: String,
    val progressPercentageLabel: String,
    val progressFraction: Float
) {
    Courses(
        stepNumber = 1,
        title = "Course Enrollment",
        progressLabel = "Step 1 of 4: Courses",
        progressPercentageLabel = "25% Complete",
        progressFraction = 0.25f
    ),
    PersonalInfo(
        stepNumber = 2,
        title = "Student Enrollment",
        progressLabel = "Step 2 of 4: Personal Info",
        progressPercentageLabel = "50% Complete",
        progressFraction = 0.50f
    )
}

fun buildEnrollableCourses(): List<EnrollableCourse> {
    return listOf(
        EnrollableCourse(
            code = "CS301",
            title = "Advanced Algorithms",
            instructorSchedule = "Dr. Sarah Jenkins \u2022 Mon/Wed 10:00 AM",
            units = 4,
            tuition = 710.0,
            isInitiallySelected = true
        ),
        EnrollableCourse(
            code = "CS305",
            title = "Database Management",
            instructorSchedule = "Prof. Michael Chen \u2022 Tue/Thu 2:00 PM",
            units = 3,
            tuition = 530.0,
            isInitiallySelected = false
        ),
        EnrollableCourse(
            code = "UI102",
            title = "User Interface Design",
            instructorSchedule = "Amanda Waller \u2022 Fri 09:00 AM",
            units = 3,
            tuition = 530.0,
            isInitiallySelected = true
        ),
        EnrollableCourse(
            code = "CS401",
            title = "Machine Learning",
            instructorSchedule = "",
            units = 4,
            tuition = 720.0,
            isInitiallySelected = false,
            isLocked = true,
            lockReason = "Prerequisite not met (CS301 required)"
        )
    )
}

fun filterEnrollableCourses(
    courses: List<EnrollableCourse>,
    searchQuery: String
): List<EnrollableCourse> {
    val normalizedQuery = searchQuery.trim()

    if (normalizedQuery.isBlank()) {
        return courses
    }

    return courses.filter { course ->
        course.code.contains(normalizedQuery, ignoreCase = true) ||
            course.title.contains(normalizedQuery, ignoreCase = true)
    }
}
