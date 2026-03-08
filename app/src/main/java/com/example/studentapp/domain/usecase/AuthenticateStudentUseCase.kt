package com.example.studentapp.domain.usecase

data class AuthenticationResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null
)

class AuthenticateStudentUseCase {
    operator fun invoke(studentId: String, password: String): AuthenticationResult {
        val normalizedStudentId = studentId.trim()

        return when {
            normalizedStudentId.isEmpty() -> {
                AuthenticationResult(
                    isSuccess = false,
                    errorMessage = "Enter your student ID."
                )
            }

            password.isBlank() -> {
                AuthenticationResult(
                    isSuccess = false,
                    errorMessage = "Enter your password."
                )
            }

            normalizedStudentId.length < 6 -> {
                AuthenticationResult(
                    isSuccess = false,
                    errorMessage = "Use a valid student ID."
                )
            }

            password.length < 6 -> {
                AuthenticationResult(
                    isSuccess = false,
                    errorMessage = "Password must be at least 6 characters."
                )
            }

            else -> {
                AuthenticationResult(isSuccess = true)
            }
        }
    }
}
