package com.example.studentapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: String,
    val refreshToken: String? = null,
    val id: String
)
