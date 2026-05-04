package com.example.studentapp.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

data class GradeRecordResponse(
    @SerializedName("id") val id: String,
    @SerializedName("studentId") val studentId: String,
    @SerializedName("subjectCode") val subjectCode: String,
    @SerializedName("subjectTitle") val subjectTitle: String,
    @SerializedName("grade") val grade: String,
    @SerializedName("credits") val credits: Int,
    @SerializedName("status") val status: String,
    @SerializedName("semester") val semester: String
)

data class ScheduleEntryResponse(
    @SerializedName("id") val id: String,
    @SerializedName("studentId") val studentId: String,
    @SerializedName("courseCode") val courseCode: String,
    @SerializedName("courseTitle") val courseTitle: String,
    @SerializedName("day") val day: String,
    @SerializedName("timeRange") val timeRange: String,
    @SerializedName("room") val room: String,
    @SerializedName("instructor") val instructor: String
)

data class PaginatedResponse<T>(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: List<T>,
    @SerializedName("meta") val meta: PaginationMeta
)

data class PaginationMeta(
    @SerializedName("total") val total: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("totalPages") val totalPages: Int
)

interface AcademicApi {
    @GET("grade-records")
    suspend fun getGradeRecords(
        @Query("studentId") studentId: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50
    ): Response<PaginatedResponse<GradeRecordResponse>>

    @GET("schedule-entries")
    suspend fun getScheduleEntries(
        @Query("studentId") studentId: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50
    ): Response<PaginatedResponse<ScheduleEntryResponse>>
}
