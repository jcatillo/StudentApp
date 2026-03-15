package com.example.studentapp.ui.screens.finance.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.studentapp.ui.theme.DarkGreen

data class Transaction(
        val title: String,
        val date: String,
        val amount: String,
        val isPaid: Boolean,
        val icon: ImageVector,
        val iconBg: Color,
        val iconTint: Color
)

val sampleTransactions =
        listOf(
                Transaction(
                        "Tuition Fee - Semester 1",
                        "Sep 12, 2023",
                        "-₱1,200.00",
                        true,
                        Icons.Default.School,
                        Color(0xFFD1FAE5),
                        Color(0xFF059669)
                ),
                Transaction(
                        "Library Late Return Fee",
                        "Aug 28, 2023",
                        "-₱15.00",
                        false,
                        Icons.Default.MenuBook,
                        Color(0xFFFEF3C7),
                        Color(0xFFF8A824)
                ),
                Transaction(
                        "Athletics Membership",
                        "Aug 15, 2023",
                        "-$50.00",
                        true,
                        Icons.Default.SportsSoccer,
                        Color(0xFFDBEAFE),
                        Color(0xFF2563EB)
                ),
                Transaction(
                        "Dormitory Rent - August",
                        "Aug 01, 2023",
                        "-$800.00",
                        true,
                        Icons.Default.HomeWork,
                        Color(0xFFE5E7EB),
                        DarkGreen
                )
        )
