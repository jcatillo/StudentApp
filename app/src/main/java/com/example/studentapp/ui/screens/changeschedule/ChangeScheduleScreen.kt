package com.example.studentapp.ui.screens.changeschedule

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.screens.changeschedule.components.*
import com.example.studentapp.ui.screens.changeschedule.models.*

@Composable
@Preview
fun ChangeScheduleScreen() {

    var sections by remember {

        mutableStateOf(
            listOf(
                SectionItem(
                    "Section 02",
                    "Prof. Mark Evans",
                    "Tue/Thu 1:00 PM",
                    "Room 402",
                    SectionStatus.AVAILABLE,
                    true
                ),
                SectionItem(
                    "Section 03",
                    "Prof. Sarah Jenkins",
                    "Mon/Wed 10:00 AM",
                    "Room 12",
                    SectionStatus.FEW_SEATS
                ),
                SectionItem(
                    "Section 05",
                    "Dr. Alan Turing",
                    "Fri 2:00 PM",
                    "Online",
                    SectionStatus.FULL
                )
            )
        )
    }

    Scaffold(

        topBar = {
            ChangeScheduleTopBar {}
        },

        containerColor = ChangeScheduleColors.Background

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                CurrentSelectionCard(
                    "CS101: Computer Science",
                    "Fall 2024",
                    "ACTIVE"
                )

            }

            items(sections) { section ->

                SectionItemCard(
                    item = section,
                    onSelect = {}
                )

            }

            item {

                ConflictInfoCard(
                    "Changing to Section 02 will free up your Monday morning slot."
                )

            }

            item {

                ConfirmScheduleButton {}

            }

        }

    }

}
