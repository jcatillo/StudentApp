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
import com.example.studentapp.ui.screens.coe.COEScreen
import com.example.studentapp.ui.screens.dashboard.DashboardScreen
import com.example.studentapp.ui.screens.finance.FinanceScreen
import com.example.studentapp.ui.screens.goodmoral.GoodMoralScreen
import com.example.studentapp.ui.screens.library.LibraryScreen
import com.example.studentapp.ui.screens.library.models.LibraryTab
import com.example.studentapp.ui.screens.login.LoginScreen
import com.example.studentapp.ui.screens.payment.PaymentQueueScreen
import com.example.studentapp.ui.screens.profile.ProfileScreen
import com.example.studentapp.ui.screens.profile.models.toUiState as toProfileUiState
import com.example.studentapp.ui.screens.schedule.ScheduleScreen
import com.example.studentapp.ui.screens.services.ServicesScreen
import com.example.studentapp.ui.screens.tor.TORScreen

@Composable
fun AppNavGraph() {
    var currentRoute by rememberSaveable {
        mutableStateOf(AppDestination.Login.route)
    }

    val authenticateStudent = remember { AuthenticateStudentUseCase() }
    val academicOverview = remember { GetAcademicOverviewUseCase().invoke().toUiState() }
    val profileOverview = remember { GetProfileOverviewUseCase().invoke().toProfileUiState() }
    val primaryBottomNavItems = remember { buildPrimaryBottomNavItems() }

    when {
        currentRoute == AppDestination.Login.route -> {
            LoginScreen(
                authenticate = authenticateStudent::invoke,
                onLoginSuccess = {
                    currentRoute = AppDestination.Dashboard.route
                }
            )
        }

        currentRoute == AppDestination.Dashboard.route -> {
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

        currentRoute == AppDestination.Academic.route -> {
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
                onContactSupportClick = {}
            )
        }

        currentRoute == AppDestination.Finance.route -> {
            BackHandler {
                currentRoute = AppDestination.Dashboard.route
            }
            FinanceScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "finance",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onPayNowClick = {
                    currentRoute = AppDestination.PaymentQueue.route
                }
            )
        }

        currentRoute == AppDestination.PaymentQueue.route -> {
            BackHandler {
                currentRoute = AppDestination.Finance.route
            }
            PaymentQueueScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "finance",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onBackClick = {
                    currentRoute = AppDestination.Finance.route
                }
            )
        }

        currentRoute == AppDestination.Services.route -> {
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
                },
                onLibraryClick = { tab ->
                    currentRoute = AppDestination.Library.createRoute(tab.name)
                },
                onTORClick = {
                    currentRoute = AppDestination.TOR.route
                },
                onCOEClick = {
                    currentRoute = AppDestination.COE.route
                },
                onGoodMoralClick = {
                    currentRoute = AppDestination.GoodMoral.route
                }
            )
        }

        currentRoute.startsWith("library/") -> {
            val tabName = currentRoute.substringAfter("library/")
            val initialTab = LibraryTab.entries.find { it.name == tabName } ?: LibraryTab.Available
            
            BackHandler {
                currentRoute = AppDestination.Services.route
            }
            LibraryScreen(
                initialTab = initialTab,
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "services",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onBackClick = {
                    currentRoute = AppDestination.Services.route
                }
            )
        }

        currentRoute == AppDestination.TOR.route -> {
            BackHandler {
                currentRoute = AppDestination.Services.route
            }
            TORScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "services",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onBackClick = {
                    currentRoute = AppDestination.Services.route
                }
            )
        }

        currentRoute == AppDestination.COE.route -> {
            BackHandler {
                currentRoute = AppDestination.Services.route
            }
            COEScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "services",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onBackClick = {
                    currentRoute = AppDestination.Services.route
                }
            )
        }

        currentRoute == AppDestination.GoodMoral.route -> {
            BackHandler {
                currentRoute = AppDestination.Services.route
            }
            GoodMoralScreen(
                navigationItems = primaryBottomNavItems,
                selectedNavItemId = "services",
                onBottomNavSelected = { item ->
                    currentRoute = resolvePrimaryRoute(item, currentRoute)
                },
                onBackClick = {
                    currentRoute = AppDestination.Services.route
                }
            )
        }

        currentRoute == AppDestination.Schedule.route -> {
            BackHandler {
                currentRoute = AppDestination.Dashboard.route
            }
            ScheduleScreen(
                onBackClick = {
                    currentRoute = AppDestination.Dashboard.route
                }
            )
        }

        currentRoute == AppDestination.Profile.route -> {
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
