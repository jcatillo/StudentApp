package com.example.studentapp.domain.model

data class AcademicOverview(
    val studentName: String,
    val programName: String,
    val yearLevel: String,
    val services: List<AcademicService>,
    val supportMessage: SupportMessage
)

data class AcademicService(
    val code: String,
    val title: String,
    val description: String
)

data class SupportMessage(
    val title: String,
    val description: String,
    val actionLabel: String
)
