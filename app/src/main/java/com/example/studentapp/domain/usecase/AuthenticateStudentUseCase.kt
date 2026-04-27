package com.example.studentapp.domain.usecase

import com.example.studentapp.data.remote.dto.LoginRequestDto
import com.example.studentapp.domain.repository.StudentRepository

data class AuthenticationResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null,
    val accessToken: String? = null
)

class AuthenticateStudentUseCase(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(studentId: String, password: String): AuthenticationResult {
        val normalizedStudentId = studentId.trim()

        if (normalizedStudentId.isEmpty()) {
            return AuthenticationResult(isSuccess = false, errorMessage = "Enter your student ID.")
        }

        if (password.isBlank()) {
            return AuthenticationResult(isSuccess = false, errorMessage = "Enter your password.")
        }

        return try {
            val response = repository.login(
                LoginRequestDto(
                    studentId = normalizedStudentId,
                    password = password
                )
            )

            if (response.isSuccessful && response.body() != null) {
                AuthenticationResult(
                    isSuccess = true,
                    accessToken = response.body()?.accessToken
                )
            } else {
                val errorMsg = when (response.code()) {
                    401 -> "Invalid student ID or password."
                    404 -> "Student not found."
                    else -> "An error occurred: ${response.message()}"
                }
                AuthenticationResult(isSuccess = false, errorMessage = errorMsg)
            }
        } catch (e: Exception) {
            AuthenticationResult(
                isSuccess = false,
                errorMessage = "Could not connect to server. Please check your internet connection."
            )
        }
    }
}
