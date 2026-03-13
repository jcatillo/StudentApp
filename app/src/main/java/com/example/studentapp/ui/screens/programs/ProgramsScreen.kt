package com.example.studentapp.ui.screens.programs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.screens.programs.components.ProgramCard
import com.example.studentapp.ui.screens.programs.components.ProgramFilterRow
import com.example.studentapp.ui.screens.programs.components.ProgramSearchBar
import com.example.studentapp.ui.screens.programs.models.ProgramBadgeType
import com.example.studentapp.ui.screens.programs.models.samplePrograms

private val ScreenBg = Color(0xFF071A10)
private val TopBarBg = Color(0xFF0B1F14)
private val Gold = Color(0xFFF3B431)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramsScreen() {
    val searchQuery = remember { mutableStateOf("") }
    val selectedFilter = remember { mutableStateOf("All") }

    val filters = listOf("All", "Undergraduate", "Postgraduate")

    val filteredPrograms = samplePrograms.filter { program ->
        val matchesSearch = program.title.contains(searchQuery.value, ignoreCase = true) ||
                program.description.contains(searchQuery.value, ignoreCase = true)

        val matchesFilter = when (selectedFilter.value) {
            "All" -> true
            "Undergraduate" -> program.badgeType == ProgramBadgeType.UNDERGRAD
            "Postgraduate" -> program.badgeType == ProgramBadgeType.POSTGRAD
            else -> true
        }

        matchesSearch && matchesFilter
    }

    Scaffold(
        containerColor = ScreenBg,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Programs",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Gold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TopBarBg
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ScreenBg)
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            ProgramSearchBar(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            ProgramFilterRow(
                filters = filters,
                selectedFilter = selectedFilter.value,
                onFilterSelected = { selectedFilter.value = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(filteredPrograms) { program ->
                    ProgramCard(program = program)
                }
            }
        }
    }
}