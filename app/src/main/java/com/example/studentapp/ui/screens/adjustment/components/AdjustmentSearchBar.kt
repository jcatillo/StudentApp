package com.example.studentapp.ui.screens.adjustment.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.screens.adjustment.AdjustmentScreenColors

@Composable
fun AdjustmentSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        placeholder = {
            Text(
                text = "Search by name, code, or professor...",
                color = AdjustmentScreenColors.TextMuted
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = AdjustmentScreenColors.TextMuted
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = AdjustmentScreenColors.SearchBackground,
            unfocusedContainerColor = AdjustmentScreenColors.SearchBackground,
            focusedBorderColor = AdjustmentScreenColors.SearchBorder,
            unfocusedBorderColor = AdjustmentScreenColors.SearchBorder,
            cursorColor = AdjustmentScreenColors.PrimaryGreen
        )
    )
}
