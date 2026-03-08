package com.example.studentapp.ui.screens.finance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.components.StudentBottomNavBar
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.theme.DarkGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceScreen(
    navigationItems: List<StudentBottomNavItem> = buildPrimaryBottomNavItems(),
    selectedNavItemId: String = "finance",
    onBottomNavSelected: (StudentBottomNavItem) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Finance", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = DarkGreen)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle notifications */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = DarkGreen)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
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
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            // Balance Card
            item {
                Spacer(modifier = Modifier.height(8.dp))
                BalanceCard()
            }

            // Quick Actions
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text("Quick Actions", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickActionButton("Payment Slip", Icons.Default.ReceiptLong, Modifier.weight(1f))
                    QuickActionButton("Assessment", Icons.Default.Description, Modifier.weight(1f))
                }
            }

            // Transaction History Header
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Transaction History", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    TextButton(onClick = { /* View All */ }) {
                        Text("View All", color = DarkGreen, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            // Transaction Items
            items(sampleTransactions) { transaction ->
                TransactionItem(transaction)
                Spacer(modifier = Modifier.height(12.dp))
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun BalanceCard() {
    val gold = Color(0xFFF8A824)
    val darkGreen = Color(0xFF1F5C23)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = gold)
    ) {
        Box(modifier = Modifier.padding(24.dp)) {
            // Simulated Glow effect from HTML
            Surface(
                modifier = Modifier.size(100.dp).align(Alignment.TopEnd).offset(x = 20.dp, y = (-20).dp),
                color = Color.White.copy(alpha = 0.2f),
                shape = CircleShape
            ) {}

            Column {
                Text("Total Outstanding Balance", color = darkGreen.copy(alpha = 0.7f), fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Text("$2,450.00", color = darkGreen, fontSize = 36.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text("DUE DATE", color = darkGreen.copy(alpha = 0.6f), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text("Oct 15, 2023", color = darkGreen, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { /* Pay */ },
                        colors = ButtonDefaults.buttonColors(containerColor = darkGreen),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Pay Now", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun QuickActionButton(label: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFF8F9FA),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = DarkGreen
            ) {
                Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.padding(8.dp))
            }
            Text(label, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF334155))
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFF8F9FA),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(8.dp),
                color = transaction.iconBg
            ) {
                Icon(
                    transaction.icon,
                    contentDescription = null,
                    tint = transaction.iconTint,
                    modifier = Modifier.padding(12.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(transaction.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(transaction.date, color = Color.Gray, fontSize = 12.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(transaction.amount, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Surface(
                    color = if (transaction.isPaid) Color(0xFFD1FAE5) else Color(0xFFFEF3C7),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        if (transaction.isPaid) "PAID" else "PENDING",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (transaction.isPaid) Color(0xFF047857) else Color(0xFFB45309)
                    )
                }
            }
        }
    }
}

data class Transaction(
    val title: String,
    val date: String,
    val amount: String,
    val isPaid: Boolean,
    val icon: ImageVector,
    val iconBg: Color,
    val iconTint: Color
)

val sampleTransactions = listOf(
    Transaction("Tuition Fee - Semester 1", "Sep 12, 2023", "-$1,200.00", true, Icons.Default.School, Color(0xFFD1FAE5), Color(0xFF059669)),
    Transaction("Library Late Return Fee", "Aug 28, 2023", "-$15.00", false, Icons.Default.MenuBook, Color(0xFFFEF3C7), Color(0xFFF8A824)),
    Transaction("Athletics Membership", "Aug 15, 2023", "-$50.00", true, Icons.Default.SportsSoccer, Color(0xFFDBEAFE), Color(0xFF2563EB)),
    Transaction("Dormitory Rent - August", "Aug 01, 2023", "-$800.00", true, Icons.Default.HomeWork, Color(0xFFE5E7EB), DarkGreen)
)