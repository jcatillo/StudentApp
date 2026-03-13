package com.example.studentapp.ui.screens.enrollment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.enrollment.EnrollmentScreenColors

@Composable
fun EnrollmentCourseSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = EnrollmentScreenColors.BackgroundSurface,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = EnrollmentScreenColors.Slate200,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, top = 14.dp, end = 16.dp, bottom = 14.dp),
            singleLine = true,
            textStyle = TextStyle(
                color = EnrollmentScreenColors.Slate900,
                fontSize = 14.sp
            ),
            cursorBrush = SolidColor(EnrollmentScreenColors.Primary),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "Search subjects or codes...",
                            color = EnrollmentScreenColors.Slate400,
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
            tint = EnrollmentScreenColors.Slate400,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp)
        )
    }
}
