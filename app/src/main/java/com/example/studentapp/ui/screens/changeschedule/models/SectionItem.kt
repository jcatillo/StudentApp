package com.example.studentapp.ui.screens.changeschedule.models

enum class SectionStatus {
    AVAILABLE,
    FEW_SEATS,
    FULL
}

data class SectionItem(
    val title: String,
    val professor: String,
    val schedule: String,
    val room: String,
    val status: SectionStatus,
    val isSelected: Boolean = false
)
