package com.example.studentapp.ui.screens.library.models

import androidx.compose.runtime.Immutable

@Immutable
enum class LibraryTab(val label: String) {
    Available("Available"),
    Return("Return"),
    History("History")
}

@Immutable
enum class StockStatus {
    Available,
    Limited,
    OutOfStock
}

@Immutable
data class LibraryBook(
    val id: String,
    val title: String,
    val author: String,
    val rating: Double,
    val genre: String,
    val stockLabel: String,
    val stockStatus: StockStatus,
    val isNew: Boolean = false,
    val tab: LibraryTab
)

@Immutable
data class FilterChip(
    val id: String,
    val label: String,
    val selected: Boolean = false
)
