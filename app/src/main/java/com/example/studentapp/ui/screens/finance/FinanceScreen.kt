package com.example.studentapp.ui.screens.finance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.components.StudentBottomNavBar
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.screens.finance.components.BalanceCard
import com.example.studentapp.ui.screens.finance.components.QuickActionsSection
import com.example.studentapp.ui.screens.finance.components.TransactionHistoryHeader
import com.example.studentapp.ui.screens.finance.components.TransactionItem
import com.example.studentapp.ui.screens.finance.models.sampleTransactions
import com.example.studentapp.ui.theme.DarkGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceScreen(
        navigationItems: List<StudentBottomNavItem> = buildPrimaryBottomNavItems(),
        selectedNavItemId: String = "finance",
        onBottomNavSelected: (StudentBottomNavItem) -> Unit = {},
        onPayNowClick: () -> Unit = {}
) {
    Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                        title = { Text("Finance", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                        actions = {
                            IconButton(onClick = { /* Handle notifications */}) {
                                Icon(
                                        Icons.Default.Notifications,
                                        contentDescription = "Notifications",
                                        tint = DarkGreen
                                )
                            }
                        },
                        colors =
                                TopAppBarDefaults.centerAlignedTopAppBarColors(
                                        containerColor = MaterialTheme.colorScheme.surface
                                )
                )
            },
            bottomBar = {
                StudentBottomNavBar(
                        items = navigationItems,
                        selectedItemId = selectedNavItemId,
                        onItemSelected = onBottomNavSelected
                )
            }
    ) { paddingValues ->
        LazyColumn(
                modifier =
                        Modifier.fillMaxSize()
                                .padding(paddingValues)
                                .background(MaterialTheme.colorScheme.background)
                                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                BalanceCard(onPayNowClick = onPayNowClick)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                QuickActionsSection()
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                TransactionHistoryHeader()
            }

            items(sampleTransactions) { transaction ->
                TransactionItem(transaction)
                Spacer(modifier = Modifier.height(12.dp))
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
