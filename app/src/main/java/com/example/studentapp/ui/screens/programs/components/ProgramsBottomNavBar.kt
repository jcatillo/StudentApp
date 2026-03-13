package com.example.studentapp.ui.screens.programs.components

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
import com.example.studentapp.ui.screens.programs.ProgramsScreenColors

@Composable
fun ProgramsBottomNavBar(
    items: List<StudentBottomNavItem>,
    selectedNavItemId: String,
    onItemSelected: (StudentBottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = ProgramsScreenColors.White.copy(alpha = 0.95f),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
        ) {
            HorizontalDivider(color = ProgramsScreenColors.Slate200)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    ProgramsBottomNavItem(
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
fun ProgramsBottomNavItem(
    item: StudentBottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val tint = when {
        isSelected -> ProgramsScreenColors.Primary
        isPressed -> ProgramsScreenColors.Primary
        else -> ProgramsScreenColors.Slate400
    }
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = resolveProgramsBottomNavIcon(item.id),
            contentDescription = item.label,
            tint = tint
        )

        Text(
            text = item.label.uppercase(),
            color = tint,
            fontSize = 10.sp,
            fontWeight = fontWeight,
            letterSpacing = 1.sp
        )
    }
}

fun resolveProgramsBottomNavIcon(id: String): ImageVector {
    return when (id) {
        "home" -> Icons.Outlined.Home
        "academic" -> Icons.Filled.School
        "finance" -> Icons.Outlined.AccountBalanceWallet
        "services" -> Icons.Outlined.GridView
        "profile" -> Icons.Outlined.Person
        else -> Icons.Outlined.Home
    }
}
