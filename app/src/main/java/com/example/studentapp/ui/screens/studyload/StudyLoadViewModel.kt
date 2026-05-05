package com.example.studentapp.ui.screens.studyload

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapp.domain.repository.AcademicRepository
import com.example.studentapp.ui.screens.studyload.models.StudyLoadItem
import kotlinx.coroutines.launch

class StudyLoadViewModel(
    private val academicRepository: AcademicRepository = com.example.studentapp.data.repository.AcademicRepositoryImpl()
) : ViewModel() {
    var subjects by mutableStateOf<List<StudyLoadItem>>(emptyList())
        private set
    
    var totalUnits by mutableStateOf(0)
        private set
        
    var semesterLabel by mutableStateOf("N/A")
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        loadStudyLoad()
    }

    fun loadStudyLoad() {
        viewModelScope.launch {
            isLoading = true
            val courses = academicRepository.getCourses()
            val enrolledCourses = courses.filter { it.status == "Enrolled" }
            
            subjects = enrolledCourses.map { response ->
                StudyLoadItem(
                    title = response.title,
                    code = response.code,
                    schedule = response.schedule ?: "TBA",
                    room = response.location ?: "TBA",
                    instructor = response.instructor ?: "TBA",
                    units = response.units ?: 0,
                    status = response.status ?: "Enrolled"
                )
            }
            
            totalUnits = subjects.sumOf { it.units }
            semesterLabel = enrolledCourses.firstOrNull()?.semesterTitle ?: "Current Semester"
            
            isLoading = false
        }
    }
}
