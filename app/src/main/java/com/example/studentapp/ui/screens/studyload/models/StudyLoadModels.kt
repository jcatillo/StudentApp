package com.example.studentapp.ui.screens.studyload.models

data class StudyLoadSummary(
    val totalUnits: Int,
    val semester: String,
    val courseCount: Int
)

data class StudyLoadItem(
    val title: String,
    val code: String,
    val schedule: String,
    val room: String,
    val instructor: String,
    val units: Int,
    val status: String
)
