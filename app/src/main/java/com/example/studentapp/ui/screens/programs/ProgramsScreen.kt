package com.example.studentapp.ui.screens.programs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.screens.programs.components.ProgramCard
import com.example.studentapp.ui.screens.programs.components.ProgramsBottomNavBar
import com.example.studentapp.ui.screens.programs.components.ProgramsHeaderSection
import com.example.studentapp.ui.screens.programs.models.ProgramEntry
import com.example.studentapp.ui.screens.programs.models.ProgramsTab
import com.example.studentapp.ui.screens.programs.models.buildProgramEntries
import com.example.studentapp.ui.screens.programs.models.filterProgramEntries
import com.example.studentapp.ui.theme.StudentAppTheme

@Composable
fun ProgramsScreen(
    navigationItems: List<StudentBottomNavItem>,
    selectedNavItemId: String,
    onBottomNavSelected: (StudentBottomNavItem) -> Unit,
    onBackClick: () -> Unit,
    onDownloadProspectusClick: (ProgramEntry) -> Unit,
    onViewProgramClick: (ProgramEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedTab by rememberSaveable { mutableStateOf(ProgramsTab.AllPrograms) }
    val programs = remember { buildProgramEntries() }
    val filteredPrograms = remember(searchQuery, selectedTab, programs) {
        filterProgramEntries(
            programs = programs,
            searchQuery = searchQuery,
            selectedTab = selectedTab
        )
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(ProgramsScreenColors.BackgroundLight),
        contentAlignment = Alignment.TopCenter
    ) {
        val useCenteredShell = maxWidth > 420.dp
        val shellModifier = if (useCenteredShell) {
            Modifier
                .padding(vertical = 12.dp)
                .fillMaxHeight()
                .fillMaxWidth(0.92f)
        } else {
            Modifier.fillMaxSize()
        }

        Surface(
            modifier = shellModifier,
            color = ProgramsScreenColors.BackgroundLight,
            shadowElevation = if (useCenteredShell) 18.dp else 0.dp
        ) {
            Scaffold(
                containerColor = ProgramsScreenColors.BackgroundLight,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                topBar = {
                    ProgramsHeaderSection(
                        searchQuery = searchQuery,
                        selectedTab = selectedTab,
                        onBackClick = onBackClick,
                        onSearchQueryChange = { searchQuery = it },
                        onTabSelected = { selectedTab = it }
                    )
                },
                bottomBar = {
                    ProgramsBottomNavBar(
                        items = navigationItems,
                        selectedNavItemId = selectedNavItemId,
                        onItemSelected = onBottomNavSelected
                    )
                }
            ) { innerPadding ->
                ProgramsContent(
                    programs = filteredPrograms,
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        top = innerPadding.calculateTopPadding() + 16.dp,
                        end = 16.dp,
                        bottom = innerPadding.calculateBottomPadding() + 16.dp
                    ),
                    onDownloadProspectusClick = onDownloadProspectusClick,
                    onViewProgramClick = onViewProgramClick
                )
            }
        }
    }
}

@Composable
fun ProgramsContent(
    programs: List<ProgramEntry>,
    contentPadding: PaddingValues,
    onDownloadProspectusClick: (ProgramEntry) -> Unit,
    onViewProgramClick: (ProgramEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding
    ) {
        items(
            items = programs,
            key = { it.title }
        ) { entry ->
            ProgramCard(
                entry = entry,
                onDownloadProspectusClick = { onDownloadProspectusClick(entry) },
                onViewProgramClick = { onViewProgramClick(entry) },
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProgramsScreenPreview() {
    StudentAppTheme(dynamicColor = false) {
        ProgramsScreen(
            navigationItems = buildPrimaryBottomNavItems(),
            selectedNavItemId = "academic",
            onBottomNavSelected = {},
            onBackClick = {},
            onDownloadProspectusClick = {},
            onViewProgramClick = {}
        )
    }
}
