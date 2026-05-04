package com.example.studentapp.domain.repository

interface FinanceRepository {
    suspend fun getBalance(studentId: String): Double?
}
