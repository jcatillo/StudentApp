package com.example.studentapp.ui.screens.evaluations.models

data class EvaluationUiState(
    val pendingCount: Int = 2,
    val selectedCourseTitle: String = "Advanced Algorithms",
    val selectedInstructor: String = "Dr. Sarah Jenkins",
    val teachingQuality: Int = 4,
    val courseMaterials: Int = 5,
    val punctuality: Int = 3,
    val additionalComments: String = "",
    val pendingCourses: List<EvaluationCourseItem> = emptyList()
)
