package com.example.studentapp.domain.usecase

import com.example.studentapp.data.remote.dto.LoginRequestDto
import com.example.studentapp.data.remote.dto.LoginResponseDto
import com.example.studentapp.data.remote.dto.StudentProfileDto
import com.example.studentapp.domain.repository.StudentRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response

class AuthenticateStudentUseCaseTest {

    // Simple mock repository for testing
    private class MockStudentRepository(val shouldSucceed: Boolean) : StudentRepository {
        override suspend fun login(request: LoginRequestDto): Response<LoginResponseDto> {
            return if (shouldSucceed) {
                Response.success(
                    LoginResponseDto(
                        accessToken = "test_token",
                        tokenType = "Bearer",
                        expiresIn = "3600",
                        id = "user_123"
                    )
                )
            } else {
                Response.error(401, okhttp3.ResponseBody.create(null, ""))
            }
        }

        override suspend fun getProfile(id: String): Response<StudentProfileDto> {
            TODO("Not needed for this test")
        }

        override suspend fun getLibraryBooks(tab: String?, page: Int, limit: Int): Response<com.example.studentapp.data.remote.dto.PagedResponse<com.example.studentapp.data.remote.dto.LibraryBookDto>> {
            TODO("Not needed for this test")
        }
    }

    @Test
    fun `invoke with valid credentials returns success`() = runBlocking {
        val repository = MockStudentRepository(shouldSucceed = true)
        val useCase = AuthenticateStudentUseCase(repository)

        val result = useCase("S1001", "password123")

        assertTrue(result.isSuccess)
        assertEquals("test_token", result.accessToken)
    }

    @Test
    fun `invoke with invalid credentials returns error`() = runBlocking {
        val repository = MockStudentRepository(shouldSucceed = false)
        val useCase = AuthenticateStudentUseCase(repository)

        val result = useCase("wrong_id", "wrong_password")

        assertFalse(result.isSuccess)
        assertEquals("Invalid student ID or password.", result.errorMessage)
    }

    @Test
    fun `invoke with empty studentId returns error`() = runBlocking {
        val repository = MockStudentRepository(shouldSucceed = true)
        val useCase = AuthenticateStudentUseCase(repository)

        val result = useCase("", "password123")

        assertFalse(result.isSuccess)
        assertEquals("Enter your student ID.", result.errorMessage)
    }
}
