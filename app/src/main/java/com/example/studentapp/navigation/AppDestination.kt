package com.example.studentapp.navigation

sealed class AppDestination(val route: String) {
    data object Login : AppDestination("login")
    data object Dashboard : AppDestination("dashboard")
    data object Academic : AppDestination("academic")
    data object Programs : AppDestination("programs")
    data object Courses : AppDestination("courses")
    data object Enrollment : AppDestination("enrollment")
    data object Schedule : AppDestination("schedule")
    data object Finance : AppDestination("finance")
    data object Services : AppDestination("services")
    data object Profile : AppDestination("profile")
    data object Library : AppDestination("library/{tab}") {
        fun createRoute(tab: String) = "library/$tab"
    }
    data object TOR : AppDestination("tor")
}
