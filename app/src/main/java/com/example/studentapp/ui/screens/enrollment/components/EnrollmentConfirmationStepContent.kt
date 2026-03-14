package com.example.studentapp.ui.screens.enrollment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Storage
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.enrollment.EnrollmentScreenColors
import com.example.studentapp.ui.screens.enrollment.models.EnrollmentConfirmationCourse
import com.example.studentapp.ui.screens.enrollment.models.EnrollmentConfirmationCourseIcon

@Composable
fun EnrollmentConfirmationStepContent(
    courses: List<EnrollmentConfirmationCourse>,
    contentPadding: PaddingValues,
    onBackClick: () -> Unit,
    onDownloadReceiptClick: () -> Unit,
    onHomeClick: () -> Unit,
    onAdjustmentClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            EnrollmentConfirmationHeader(onBackClick = onBackClick)
        }

        item {
            EnrollmentConfirmationSuccessSection()
        }

        item {
            Text(
                text = "Enrolled Courses",
                color = EnrollmentScreenColors.Slate500,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
        }

        items(courses) { course ->
            EnrollmentConfirmationCourseItem(course = course)
        }

        item {
            EnrollmentConfirmationActions(
                onDownloadReceiptClick = onDownloadReceiptClick,
                onHomeClick = onHomeClick,
                onAdjustmentClick = onAdjustmentClick
            )
        }
    }
}

@Composable
fun EnrollmentConfirmationHeader(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickable(onClick = onBackClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = EnrollmentScreenColors.Slate900
                )
            }

            Text(
                text = buildAnnotatedString {
                    append("STEP 04: ")
                    withStyle(SpanStyle(color = EnrollmentScreenColors.Highlight)) {
                        append("CONF")
                    }
                },
                modifier = Modifier.weight(1f),
                color = EnrollmentScreenColors.Slate900,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Box(modifier = Modifier.size(48.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Enrollment Progress",
                color = EnrollmentScreenColors.Slate900,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "4/4",
                color = EnrollmentScreenColors.Slate900,
                fontSize = 14.sp
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(
                    color = EnrollmentScreenColors.Slate100,
                    shape = CircleShape
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(
                        color = EnrollmentScreenColors.Highlight,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun EnrollmentConfirmationSuccessSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(
                    color = EnrollmentScreenColors.Primary.copy(alpha = 0.10f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = EnrollmentScreenColors.Primary,
                modifier = Modifier.size(56.dp)
            )
        }

        Text(
            text = "ENROLLMENT SUCCESSFUL",
            color = EnrollmentScreenColors.Slate900,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Congratulations! You have been successfully enrolled for the Fall 2024 semester.",
            color = EnrollmentScreenColors.Slate500,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EnrollmentConfirmationCourseItem(
    course: EnrollmentConfirmationCourse,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = EnrollmentScreenColors.BackgroundSurface.copy(alpha = 0.50f),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = EnrollmentScreenColors.Slate100,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = EnrollmentScreenColors.Primary.copy(alpha = 0.10f),
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = confirmationIcon(course.iconType),
                contentDescription = null,
                tint = EnrollmentScreenColors.Primary
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = course.title,
                color = EnrollmentScreenColors.Slate900,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = course.subtitle,
                color = EnrollmentScreenColors.Slate500,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun EnrollmentConfirmationActions(
    onDownloadReceiptClick: () -> Unit,
    onHomeClick: () -> Unit,
    onAdjustmentClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        EnrollmentPrimaryButton(
            text = "DOWNLOAD_RECEIPT",
            icon = Icons.Outlined.Download,
            onClick = onDownloadReceiptClick
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            EnrollmentSecondaryButton(
                text = "HOME",
                onClick = onHomeClick,
                modifier = Modifier.weight(1f)
            )
            EnrollmentSecondaryButton(
                text = "ADJUSTMENT",
                onClick = onAdjustmentClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun EnrollmentSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = EnrollmentScreenColors.CardLight,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = EnrollmentScreenColors.Primary.copy(alpha = 0.20f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = EnrollmentScreenColors.Primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun confirmationIcon(iconType: EnrollmentConfirmationCourseIcon): ImageVector {
    return when (iconType) {
        // HTML uses `terminal`; `Code` is the closest official Material icon exposed in Compose.
        EnrollmentConfirmationCourseIcon.Code -> Icons.Outlined.Code
        // HTML uses `database`; `Storage` is the closest official Material icon exposed in Compose.
        EnrollmentConfirmationCourseIcon.Database -> Icons.Outlined.Storage
        EnrollmentConfirmationCourseIcon.Design -> Icons.Outlined.Palette
        EnrollmentConfirmationCourseIcon.Generic -> Icons.AutoMirrored.Outlined.MenuBook
    }
}
