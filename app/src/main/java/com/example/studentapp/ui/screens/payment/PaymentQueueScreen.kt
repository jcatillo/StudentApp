package com.example.studentapp.ui.screens.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.components.StudentBottomNavBar
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.screens.payment.components.GetPriorityNumberAction
import com.example.studentapp.ui.screens.payment.components.LiveStatusCard
import com.example.studentapp.ui.screens.payment.components.OfficeLocationCard
import com.example.studentapp.ui.screens.payment.components.PaymentQueueHeader
import com.example.studentapp.ui.screens.payment.components.PaymentQueueNote
import com.example.studentapp.ui.screens.payment.components.QueueStatsGrid

@Composable
fun PaymentQueueScreen(
    navigationItems: List<StudentBottomNavItem> = buildPrimaryBottomNavItems(),
    selectedNavItemId: String = "finance",
    onBottomNavSelected: (StudentBottomNavItem) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            PaymentQueueHeader(onBackClick = onBackClick)
        },
        bottomBar = {
            StudentBottomNavBar(
                items = navigationItems,
                selectedItemId = selectedNavItemId,
                onItemSelected = onBottomNavSelected
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            LiveStatusCard()

            GetPriorityNumberAction()

            QueueStatsGrid()

            PaymentQueueNote()

            OfficeLocationCard()

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
