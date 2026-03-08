package com.example.studentapp.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.components.StudentBottomNavBar
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.screens.dashboard.components.CampusDigitalIdCard
import com.example.studentapp.ui.screens.dashboard.components.DashboardHeader
import com.example.studentapp.ui.screens.dashboard.components.RequestStatusSection
import com.example.studentapp.ui.screens.dashboard.components.StatsSection
import com.example.studentapp.ui.screens.dashboard.components.StudyLoadSection
import com.example.studentapp.ui.screens.dashboard.models.DashboardUiState
import com.example.studentapp.ui.screens.dashboard.models.buildDashboardUiState
import com.example.studentapp.ui.theme.BackgroundLight
import com.example.studentapp.ui.theme.StudentAppTheme

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    state: DashboardUiState = buildDashboardUiState(),
    navigationItems: List<StudentBottomNavItem> = buildPrimaryBottomNavItems(),
    selectedNavItemId: String = "home",
    onBottomNavSelected: (StudentBottomNavItem) -> Unit = {},
    onViewScheduleClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = BackgroundLight,
        topBar = {
            DashboardHeader(
                studentName = state.studentName,
                hasUnreadNotifications = true
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 24.dp,
                top = innerPadding.calculateTopPadding() + 16.dp,
                end = 24.dp,
                bottom = innerPadding.calculateBottomPadding() + 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                StatsSection(stats = state.stats)
            }

            item {
                CampusDigitalIdCard(
                    studentName = state.studentName,
                    studentId = state.studentId
                )
            }

            item {
                StudyLoadSection(
                    courses = state.courses,
                    onViewScheduleClick = onViewScheduleClick
                )
            }

            item {
                RequestStatusSection(requestStatus = state.requestStatus)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    StudentAppTheme(dynamicColor = false) {
        DashboardScreen()
    }
}
