package com.example.studentapp.domain.repository

import com.example.studentapp.data.remote.dto.LoginRequestDto
import com.example.studentapp.data.remote.dto.LoginResponseDto
import com.example.studentapp.data.remote.dto.StudentProfileDto
import retrofit2.Response

interface StudentRepository {
    suspend fun login(request: LoginRequestDto): Response<LoginResponseDto>
    suspend fun getProfile(id: String): Response<StudentProfileDto>
}
