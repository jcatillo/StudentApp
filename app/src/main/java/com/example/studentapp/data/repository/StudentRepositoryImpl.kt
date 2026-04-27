package com.example.studentapp.data.repository

import com.example.studentapp.data.remote.StudentApi
import com.example.studentapp.data.remote.dto.LoginRequestDto
import com.example.studentapp.data.remote.dto.LoginResponseDto
import com.example.studentapp.data.remote.dto.StudentProfileDto
import com.example.studentapp.domain.repository.StudentRepository
import retrofit2.Response

class StudentRepositoryImpl(
    private val api: StudentApi
) : StudentRepository {
    override suspend fun login(request: LoginRequestDto): Response<LoginResponseDto> {
        return api.login(request)
    }

    override suspend fun getProfile(id: String): Response<StudentProfileDto> {
        return api.getProfile(id)
    }
}
