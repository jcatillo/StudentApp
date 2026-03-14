package com.example.studentapp.ui.screens.programs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.programs.ProgramsScreenColors

@Composable
fun ProgramsSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .background(
                color = ProgramsScreenColors.Slate100,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = if (isFocused) {
                    ProgramsScreenColors.Primary
                } else {
                    ProgramsScreenColors.Slate200
                },
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 44.dp, top = 14.dp, end = 16.dp, bottom = 14.dp)
                .onFocusChanged { isFocused = it.hasFocus },
            singleLine = true,
            textStyle = TextStyle(
                color = ProgramsScreenColors.Slate900,
                fontSize = 14.sp
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            ),
            cursorBrush = SolidColor(ProgramsScreenColors.Primary),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "Search programs...",
                            color = ProgramsScreenColors.Slate500,
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                }
            }
        )

        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Search",
            tint = if (isFocused) {
                ProgramsScreenColors.Primary
            } else {
                ProgramsScreenColors.Primary.copy(alpha = 0.60f)
            },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp)
        )
    }
}
