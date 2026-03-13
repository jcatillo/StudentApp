package com.example.studentapp.ui.screens.library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.components.StudentBottomNavBar
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.screens.library.models.FilterChip
import com.example.studentapp.ui.screens.library.models.LibraryBook
import com.example.studentapp.ui.screens.library.models.LibraryTab
import com.example.studentapp.ui.screens.library.models.sampleLibraryBooks
import com.example.studentapp.ui.screens.library.models.sampleLibraryFilters

@Composable
fun LibraryContent(
    initialTab: LibraryTab = LibraryTab.Available,
    navigationItems: List<StudentBottomNavItem> = buildPrimaryBottomNavItems(),
    selectedNavItemId: String = "services",
    onBottomNavSelected: (StudentBottomNavItem) -> Unit = {},
    onBackClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onTuneClick: () -> Unit = {}
) {
    var selectedTab by rememberSaveable { mutableStateOf(initialTab) }
    var query by rememberSaveable { mutableStateOf("") }

    // Update selectedTab if initialTab changes (e.g. on navigation)
    LaunchedEffect(initialTab) {
        selectedTab = initialTab
    }

    val filteredBooks = remember(selectedTab, query) {
        filterLibraryBooks(
            books = sampleLibraryBooks,
            selectedTab = selectedTab,
            query = query
        )
    }

    val filters = remember { sampleLibraryFilters }

    Scaffold(
        bottomBar = {
            StudentBottomNavBar(
                items = navigationItems,
                selectedItemId = selectedNavItemId,
                onItemSelected = onBottomNavSelected
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            LibraryHeaderSection(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                onBackClick = onBackClick,
                onNotificationClick = onNotificationClick
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                item {
                    LibrarySearchAndFilters(
                        query = query,
                        filters = filters,
                        onQueryChange = { query = it },
                        onTuneClick = onTuneClick
                    )
                }

                item {
                    LibraryBookList(
                        books = filteredBooks,
                        selectedTab = selectedTab
                    )
                }

                item {
                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(bottom = 12.dp))
                }
            }
        }
    }
}

private fun filterLibraryBooks(
    books: List<LibraryBook>,
    selectedTab: LibraryTab,
    query: String
): List<LibraryBook> {
    return books
        .filter { book ->
            when (selectedTab) {
                LibraryTab.Available -> book.tab == LibraryTab.Available
                LibraryTab.Return -> book.tab == LibraryTab.Return
                LibraryTab.History -> book.tab == LibraryTab.History
            }
        }
        .filter { book ->
            query.isBlank() ||
                book.title.contains(query, ignoreCase = true) ||
                book.author.contains(query, ignoreCase = true)
        }
}
