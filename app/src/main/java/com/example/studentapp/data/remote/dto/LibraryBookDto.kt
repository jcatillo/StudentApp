package com.example.studentapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LibraryBookDto(
    val id: String,
    val title: String,
    val author: String,
    val rating: Double,
    val genre: String,
    val stockLabel: String,
    val stockStatus: String,
    val isNew: Boolean,
    val tab: String
)

@Serializable
data class PagedResponse<T>(
    val data: List<T>,
    val meta: PaginationMeta
)

@Serializable
data class PaginationMeta(
    val total: Int,
    val page: Int,
    val limit: Int,
    val totalPages: Int
)
