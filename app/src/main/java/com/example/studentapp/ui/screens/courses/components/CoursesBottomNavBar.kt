package com.example.studentapp.ui.screens.courses.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.screens.courses.CoursesScreenColors

@Composable
fun CoursesBottomNavBar(
    items: List<StudentBottomNavItem>,
    selectedNavItemId: String,
    onItemSelected: (StudentBottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = CoursesScreenColors.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 24.dp)
        ) {
            HorizontalDivider(color = CoursesScreenColors.Slate200)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items.forEach { item ->
                    CoursesBottomNavItem(
                        item = item,
                        isSelected = item.id == selectedNavItemId,
                        onClick = { onItemSelected(item) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun CoursesBottomNavItem(
    item: StudentBottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val tint = when {
        isSelected -> CoursesScreenColors.Primary
        isPressed -> CoursesScreenColors.Primary
        else -> CoursesScreenColors.Slate400
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.height(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = resolveCoursesBottomNavIcon(item.id),
                contentDescription = item.label,
                tint = tint
            )
        }

        Text(
            text = item.label,
            color = tint,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            letterSpacing = 0.18.sp
        )
    }
}

fun resolveCoursesBottomNavIcon(id: String): ImageVector {
    return when (id) {
        "home" -> Icons.Outlined.Home
        "academic" -> Icons.Filled.School
        "finance" -> Icons.Outlined.AccountBalanceWallet
        "services" -> Icons.Outlined.GridView
        "profile" -> Icons.Outlined.Person
        else -> Icons.Outlined.Home
    }
}
