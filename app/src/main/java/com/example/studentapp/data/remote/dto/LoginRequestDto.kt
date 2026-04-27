package com.example.studentapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val studentId: String,
    val password: String,
    val keepLoggedIn: Boolean = false
)
