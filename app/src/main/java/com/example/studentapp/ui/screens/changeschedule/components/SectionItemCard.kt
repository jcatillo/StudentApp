package com.example.studentapp.ui.screens.changeschedule.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.example.studentapp.ui.screens.changeschedule.ChangeScheduleColors
import com.example.studentapp.ui.screens.changeschedule.models.SectionItem
import com.example.studentapp.ui.screens.changeschedule.models.SectionStatus

@Composable
fun SectionItemCard(
    item: SectionItem,
    onSelect: () -> Unit
) {

    val borderColor =
        if (item.isSelected) ChangeScheduleColors.Yellow
        else ChangeScheduleColors.Border

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, borderColor, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        onClick = onSelect
    ) {

        Row(
            Modifier.padding(14.dp)
        ) {

            Column(
                Modifier.weight(1f)
            ) {

                Text(
                    item.title,
                    fontWeight = FontWeight.Bold
                )

                Text(item.professor)
                Text(item.schedule)

                when (item.status) {

                    SectionStatus.AVAILABLE ->
                        Text("Available", color = ChangeScheduleColors.Available)

                    SectionStatus.FEW_SEATS ->
                        Text("Few seats left", color = ChangeScheduleColors.Yellow)

                    SectionStatus.FULL ->
                        Row {
                            Icon(Icons.Outlined.Lock, null)
                            Text("Full")
                        }
                }

            }

            RadioButton(
                selected = item.isSelected,
                onClick = onSelect
            )

        }

    }
}
