package com.example.studentapp.ui.screens.adjustment.models

data class AdjustmentUiState(
    val semesterLabel: String = "SPRING 2024",
    val currentLoad: Int = 15,
    val maxLoad: Int = 21,
    val enrolledCourses: List<AdjustmentCourseItem> = emptyList(),
    val searchQuery: String = ""
)
