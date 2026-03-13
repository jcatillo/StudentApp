package com.example.studentapp.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.studentapp.domain.usecase.AuthenticateStudentUseCase
import com.example.studentapp.domain.usecase.GetAcademicOverviewUseCase
import com.example.studentapp.domain.usecase.GetProfileOverviewUseCase
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.screens.academic.AcademicScreen
import com.example.studentapp.ui.screens.academic.models.toUiState
import com.example.studentapp.ui.screens.courses.CoursesScreen
import com.example.studentapp.ui.screens.dashboard.DashboardScreen
import com.example.studentapp.ui.screens.finance.FinanceScreen
import com.example.studentapp.ui.screens.login.LoginScreen
import com.example.studentapp.ui.screens.profile.ProfileScreen
import com.example.studentapp.ui.screens.programs.ProgramsScreen
import com.example.studentapp.ui.screens.profile.models.toUiState as toProfileUiState
import com.example.studentapp.ui.screens.schedule.ScheduleScreen
import com.example.studentapp.ui.screens.services.ServicesScreen

@Composable
fun AppNavGraph() {
    var currentRoute by rememberSaveable {
        mutableStateOf(AppDestination.Login.route)
    }

    val authenticateStudent = remember { AuthenticateStudentUseCase() }
    val academicOverview = remember { GetAcademicOverviewUseCase().invoke().toUiState() }
    val profileOverview = remember { GetProfileOverviewUseCase().invoke().toProfileUiState() }
    val primaryBottomNavItems = remember { buildPrimaryBottomNavItems() }

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
            BackHandler {
                currentRoute = AppDestination.Login.route
            }
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
            BackHandler {
                currentRoute = AppDestination.Dashboard.route
            }
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
                onContactSupportClick = {},
                onCoursesClick = {
                    currentRoute = AppDestination.Courses.route
                },
                onProgramsClick = {
                    currentRoute = AppDestination.Programs.route
                }
            )
        }

        AppDestination.Programs.route -> {
            BackHandler {
                currentRoute = AppDestination.Academic.route
            }
            ProgramsScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "academic",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onBackClick = {
                    currentRoute = AppDestination.Academic.route
                },
                onDownloadProspectusClick = {},
                onViewProgramClick = {}
            )
        }

        AppDestination.Courses.route -> {
            BackHandler {
                currentRoute = AppDestination.Academic.route
            }
            CoursesScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "academic",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onBackClick = {
                    currentRoute = AppDestination.Academic.route
                }
            )
        }

        AppDestination.Finance.route -> {
            BackHandler {
                currentRoute = AppDestination.Dashboard.route
            }
            FinanceScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "finance",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                }
            )
        }

        AppDestination.Services.route -> {
            BackHandler {
                currentRoute = AppDestination.Dashboard.route
            }
            ServicesScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "services",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onBackClick = {
                    currentRoute = AppDestination.Dashboard.route
                }
            )
        }

        AppDestination.Schedule.route -> {
            BackHandler {
                currentRoute = AppDestination.Dashboard.route
            }
            ScheduleScreen(
                onBackClick = {
                    currentRoute = AppDestination.Dashboard.route
                }
            )
        }

        AppDestination.Profile.route -> {
            BackHandler {
                currentRoute = AppDestination.Dashboard.route
            }
            ProfileScreen(
                state = profileOverview,
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "profile",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
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
        "services" -> AppDestination.Services.route
        "profile" -> AppDestination.Profile.route
        else -> currentRoute
    }
}
