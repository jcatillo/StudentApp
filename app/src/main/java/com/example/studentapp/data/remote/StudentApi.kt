package com.example.studentapp.data.remote

import com.example.studentapp.data.remote.dto.LibraryBookDto
import com.example.studentapp.data.remote.dto.LoginRequestDto
import com.example.studentapp.data.remote.dto.LoginResponseDto
import com.example.studentapp.data.remote.dto.PagedResponse
import com.example.studentapp.data.remote.dto.StudentProfileDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentApi {

    @POST("api/v1/auth/login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): Response<LoginResponseDto>

    @GET("api/v1/students/{id}")
    suspend fun getProfile(
        @Path("id") id: String
    ): Response<StudentProfileDto>

    @GET("api/v1/library-books")
    suspend fun getLibraryBooks(
        @Query("tab") tab: String? = null,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<PagedResponse<LibraryBookDto>>

    companion object {
        const val BASE_URL = "http://10.0.2.2:3000" // No trailing slash
    }
}
