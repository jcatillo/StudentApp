package com.example.studentapp.ui.screens.programs.models

enum class ProgramBadgeType {
    UNDERGRAD,
    POSTGRAD,
    SHORT_COURSE
}

enum class ProgramStatusType {
    OPEN,
    CLOSING_SOON,
    NEXT_INTAKE
}

data class Program(
    val id: Int,
    val title: String,
    val badge: String,
    val badgeType: ProgramBadgeType,
    val durationYears: Int? = null,
    val durationMonths: Int? = null,
    val description: String,
    val statusLabel: String,
    val statusType: ProgramStatusType
)

data class ProgramsUiState(
    val searchQuery: String = "",
    val selectedFilter: String = "All",
    val programs: List<Program> = samplePrograms
)

val samplePrograms = listOf(
    Program(
        id = 1,
        title = "BS Computer Science",
        badge = "UNDERGRAD",
        badgeType = ProgramBadgeType.UNDERGRAD,
        durationYears = 4,
        description = "A comprehensive program covering software engineering, artificial intelligence, and advanced data structures for the next generation of developers.",
        statusLabel = "Enrollment Open",
        statusType = ProgramStatusType.OPEN
    ),
    Program(
        id = 2,
        title = "MS Data Science",
        badge = "POSTGRAD",
        badgeType = ProgramBadgeType.POSTGRAD,
        durationYears = 2,
        description = "Focus on machine learning, big data analytics, and statistical modeling to solve complex real-world challenges.",
        statusLabel = "Applications closing soon",
        statusType = ProgramStatusType.CLOSING_SOON
    ),
    Program(
        id = 3,
        title = "Cybersecurity Essentials",
        badge = "SHORT COURSE",
        badgeType = ProgramBadgeType.SHORT_COURSE,
        durationMonths = 6,
        description = "Intensive training in network security, ethical hacking, and threat mitigation for industry certification.",
        statusLabel = "Financial Aid Available",
        statusType = ProgramStatusType.OPEN
    ),
    Program(
        id = 4,
        title = "BBA Business Admin",
        badge = "UNDERGRAD",
        badgeType = ProgramBadgeType.UNDERGRAD,
        durationYears = 4,
        description = "Prepare for leadership roles with a strong foundation in finance, marketing, and organizational management.",
        statusLabel = "Next intake: Sept 2024",
        statusType = ProgramStatusType.NEXT_INTAKE
    )
)