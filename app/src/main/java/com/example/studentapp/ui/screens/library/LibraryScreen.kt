package com.example.studentapp.ui.screens.library

import androidx.compose.runtime.Composable
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.screens.library.components.LibraryContent
import com.example.studentapp.ui.screens.library.models.LibraryTab

@Composable
fun LibraryScreen(
    initialTab: LibraryTab = LibraryTab.Available,
    navigationItems: List<StudentBottomNavItem> = buildPrimaryBottomNavItems(),
    selectedNavItemId: String = "services",
    onBottomNavSelected: (StudentBottomNavItem) -> Unit = {},
    onBackClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onTuneClick: () -> Unit = {}
) {
    LibraryContent(
        initialTab = initialTab,
        navigationItems = navigationItems,
        selectedNavItemId = selectedNavItemId,
        onBottomNavSelected = onBottomNavSelected,
        onBackClick = onBackClick,
        onNotificationClick = onNotificationClick,
        onTuneClick = onTuneClick
    )
}
