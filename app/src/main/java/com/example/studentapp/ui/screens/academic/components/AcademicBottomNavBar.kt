package com.example.studentapp.ui.screens.academic.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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
import com.example.studentapp.ui.screens.academic.AcademicScreenColors

@Composable
fun AcademicBottomNavBar(
    items: List<StudentBottomNavItem>,
    selectedNavItemId: String,
    onItemSelected: (StudentBottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = AcademicScreenColors.BackgroundLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
        ) {
            HorizontalDivider(color = AcademicScreenColors.BorderLight)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    AcademicBottomNavItem(
                        item = item,
                        isSelected = item.id == selectedNavItemId,
                        onClick = { onItemSelected(item) }
                    )
                }
            }
        }
    }
}

@Composable
fun AcademicBottomNavItem(
    item: StudentBottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val tint = when {
        isSelected -> AcademicScreenColors.Accent
        isPressed -> AcademicScreenColors.Primary
        else -> AcademicScreenColors.Slate400
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = academicBottomNavIcon(item.id, isSelected),
            contentDescription = item.label,
            tint = tint
        )

        Text(
            text = item.label,
            color = tint,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.2).sp
        )
    }
}

fun academicBottomNavIcon(id: String, isSelected: Boolean): ImageVector {
    return when (id) {
        "home" -> if (isSelected) Icons.Filled.Home else Icons.Outlined.Home
        "academic" -> Icons.Filled.School
        "finance" -> if (isSelected) {
            Icons.Filled.AccountBalanceWallet
        } else {
            Icons.Outlined.AccountBalanceWallet
        }

        "services" -> if (isSelected) Icons.Filled.GridView else Icons.Outlined.GridView
        "profile" -> if (isSelected) Icons.Filled.Person else Icons.Outlined.Person
        else -> academicFallbackBottomNavIcon(isSelected)
    }
}

fun academicFallbackBottomNavIcon(isSelected: Boolean): ImageVector {
    return if (isSelected) {
        Icons.Filled.Home
    } else {
        Icons.Outlined.Home
    }
}
