package com.example.studentapp.ui.screens.finance.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.finance.models.Transaction
import com.example.studentapp.ui.theme.DarkGreen

@Composable
fun TransactionHistoryHeader(onViewAllClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Transaction History", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        TextButton(onClick = onViewAllClick) {
            Text("View All", color = DarkGreen, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFF8F9FA),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
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
