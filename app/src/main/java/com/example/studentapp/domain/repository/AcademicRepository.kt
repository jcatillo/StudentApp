package com.example.studentapp.domain.repository

import com.example.studentapp.data.remote.GradeRecordResponse
import com.example.studentapp.data.remote.ScheduleEntryResponse

interface AcademicRepository {
    suspend fun getGradeRecords(studentId: String): List<GradeRecordResponse>
    suspend fun getScheduleEntries(studentId: String): List<ScheduleEntryResponse>
}
