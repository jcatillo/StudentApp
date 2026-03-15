package com.example.studentapp.ui.screens.grades.models

data class GradesUiState(
    val gpa: String = "3.85",
    val academicLevel: String = "Junior",
    val filters: List<GradeSemesterFilter> = emptyList(),
    val subjects: List<GradesSubjectItem> = emptyList()
)
