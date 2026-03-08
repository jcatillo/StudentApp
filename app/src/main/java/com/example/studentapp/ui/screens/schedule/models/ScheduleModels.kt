package com.example.studentapp.ui.screens.schedule.models

import androidx.compose.runtime.Immutable

@Immutable
data class ScheduleUiState(
    val studentName: String,
    val sections: List<ScheduleDaySection>
)

@Immutable
data class ScheduleDaySection(
    val dayLabel: String,
    val entries: List<ScheduleEntry>
)

@Immutable
data class ScheduleEntry(
    val courseCode: String,
    val courseTitle: String,
    val timeRange: String,
    val room: String,
    val instructor: String
)

fun buildScheduleUiState(): ScheduleUiState {
    return ScheduleUiState(
        studentName = "Alex Harrison",
        sections = listOf(
            ScheduleDaySection(
                dayLabel = "Monday",
                entries = listOf(
                    ScheduleEntry(
                        courseCode = "CS301",
                        courseTitle = "Advanced Algorithms",
                        timeRange = "09:00 AM - 10:30 AM",
                        room = "Room 402",
                        instructor = "Prof. Sanchez"
                    ),
                    ScheduleEntry(
                        courseCode = "IT220",
                        courseTitle = "Human Computer Interaction",
                        timeRange = "01:00 PM - 02:30 PM",
                        room = "Lab 3",
                        instructor = "Prof. Tan"
                    )
                )
            ),
            ScheduleDaySection(
                dayLabel = "Tuesday",
                entries = listOf(
                    ScheduleEntry(
                        courseCode = "SE210",
                        courseTitle = "Software Engineering",
                        timeRange = "10:00 AM - 11:30 AM",
                        room = "Room 215",
                        instructor = "Prof. Reyes"
                    )
                )
            ),
            ScheduleDaySection(
                dayLabel = "Thursday",
                entries = listOf(
                    ScheduleEntry(
                        courseCode = "UI202",
                        courseTitle = "Interaction Design Studio",
                        timeRange = "02:30 PM - 04:00 PM",
                        room = "Design Hub",
                        instructor = "Prof. Lim"
                    )
                )
            )
        )
    )
}
