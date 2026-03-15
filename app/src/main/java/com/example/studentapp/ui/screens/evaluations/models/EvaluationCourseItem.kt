package com.example.studentapp.ui.screens.evaluations.models

data class EvaluationCourseItem(
    val codeTitle: String,
    val instructor: String,
    val iconType: EvaluationCourseIconType
)

enum class EvaluationCourseIconType {
    DOCUMENT,
    CHART
}
