package com.example.studentapp.ui.screens.academic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentapp.domain.usecase.GetAcademicOverviewUseCase
import com.example.studentapp.ui.components.StudentBottomNavBar
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.screens.academic.components.AcademicHeader
import com.example.studentapp.ui.screens.academic.components.AcademicHeroCard
import com.example.studentapp.ui.screens.academic.components.AcademicServiceCard
import com.example.studentapp.ui.screens.academic.components.AcademicSectionHeader
import com.example.studentapp.ui.screens.academic.components.SupportCard
import com.example.studentapp.ui.screens.academic.models.AcademicUiState
import com.example.studentapp.ui.screens.academic.models.toUiState
import com.example.studentapp.ui.theme.BackgroundLight
import com.example.studentapp.ui.theme.StudentAppTheme

@Composable
fun AcademicScreen(
    state: AcademicUiState,
    navigationItems: List<StudentBottomNavItem>,
    selectedNavItemId: String,
    onBottomNavSelected: (StudentBottomNavItem) -> Unit,
    onBackClick: () -> Unit,
    onViewAllClick: () -> Unit,
    onContactSupportClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = BackgroundLight,
        topBar = {
            AcademicHeader(
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            StudentBottomNavBar(
                items = navigationItems,
                selectedItemId = selectedNavItemId,
                onItemSelected = onBottomNavSelected
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 148.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = innerPadding.calculateTopPadding() + 16.dp,
                end = 16.dp,
                bottom = innerPadding.calculateBottomPadding() + 24.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                AcademicHeroCard(
                    studentName = state.studentName,
                    programSummary = state.programSummary
                )
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                AcademicSectionHeader(
                    title = "Student Dashboard",
                    onViewAllClick = onViewAllClick
                )
            }

            items(state.services) { service ->
                AcademicServiceCard(service = service)
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                SupportCard(
                    title = state.supportCard.title,
                    description = state.supportCard.description,
                    actionLabel = state.supportCard.actionLabel,
                    onContactSupportClick = onContactSupportClick
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AcademicScreenPreview() {
    StudentAppTheme(dynamicColor = false) {
        AcademicScreen(
            state = GetAcademicOverviewUseCase().invoke().toUiState(),
            navigationItems = buildPrimaryBottomNavItems(),
            selectedNavItemId = "academic",
            onBottomNavSelected = {},
            onBackClick = {},
            onViewAllClick = {},
            onContactSupportClick = {}
        )
    }
}
