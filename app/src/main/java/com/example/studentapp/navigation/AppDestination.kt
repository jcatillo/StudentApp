package com.example.studentapp.navigation

sealed class AppDestination(val route: String) {
    data object Login : AppDestination("login")
    data object Dashboard : AppDestination("dashboard")
    data object Academic : AppDestination("academic")
    data object Schedule : AppDestination("schedule")
}
