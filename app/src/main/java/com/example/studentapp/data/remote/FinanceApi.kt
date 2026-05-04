package com.example.studentapp.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

data class BalanceResponse(
    @SerializedName("studentId") val studentId: String,
    @SerializedName("balance") val balance: Double,
    @SerializedName("currency") val currency: String
)

interface FinanceApi {
    @GET("finance/balance/{studentId}")
    suspend fun getBalance(@Path("studentId") studentId: String): Response<ApiResponse<BalanceResponse>>
}
