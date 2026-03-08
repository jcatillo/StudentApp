package com.example.studentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.studentapp.domain.usecase.GetAcademicOverviewUseCase
import com.example.studentapp.domain.usecase.AuthenticateStudentUseCase
import com.example.studentapp.navigation.AppDestination
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.screens.academic.AcademicScreen
import com.example.studentapp.ui.screens.dashboard.DashboardScreen
import com.example.studentapp.ui.screens.login.LoginScreen
import com.example.studentapp.ui.screens.finance.FinanceScreen
import com.example.studentapp.ui.screens.schedule.ScheduleScreen
import com.example.studentapp.ui.screens.academic.models.toUiState
import com.example.studentapp.ui.theme.StudentAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentAppTheme {
                StudentAppRoot()
            }
        }
    }
}

@Composable
private fun StudentAppRoot() {
    var currentRoute by rememberSaveable {
        mutableStateOf(AppDestination.Login.route)
    }

    val authenticateStudent = remember {
        AuthenticateStudentUseCase()
    }

    val academicOverview = remember {
        GetAcademicOverviewUseCase().invoke().toUiState()
    }

    val primaryBottomNavItems = remember {
        buildPrimaryBottomNavItems()
    }

    when (currentRoute) {
        AppDestination.Login.route -> {
            LoginScreen(
                authenticate = authenticateStudent::invoke,
                onLoginSuccess = {
                    currentRoute = AppDestination.Dashboard.route
                }
            )
        }

        AppDestination.Dashboard.route -> {
            DashboardScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "home",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onViewScheduleClick = {
                    currentRoute = AppDestination.Schedule.route
                }
            )
        }

        AppDestination.Academic.route -> {
            AcademicScreen(
                state = academicOverview,
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "academic",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onBackClick = {
                    currentRoute = AppDestination.Dashboard.route
                },
                onViewAllClick = {},
                onContactSupportClick = {}
            )
        }

        AppDestination.Finance.route -> {
            FinanceScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "finance",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                }
            )
        }

        AppDestination.Schedule.route -> {
            ScheduleScreen(
                onBackClick = {
                    currentRoute = AppDestination.Dashboard.route
                }
            )
        }
    }
}

private fun resolvePrimaryRoute(
    item: StudentBottomNavItem,
    currentRoute: String
): String {
    return when (item.id) {
        "home" -> AppDestination.Dashboard.route
        "academic" -> AppDestination.Academic.route
        "finance" -> AppDestination.Finance.route
        else -> currentRoute
    }
}
