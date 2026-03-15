package com.example.studentapp.ui.screens.changeschedule.models

data class ChangeScheduleUiState(
    val courseTitle: String,
    val semester: String,
    val sections: List<SectionItem>
)
