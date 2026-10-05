package com.example.farmrecordapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmrecordapp.repository.FarmRepository
import com.example.farmrecordapp.ui.components.BottomTab
import com.example.farmrecordapp.ui.components.FarmBottomNavBar
import com.example.farmrecordapp.ui.components.FarmTopBar

@Composable
fun ExpenseRecordsScreen(
    onBack: () -> Unit,
    onAddExpenseClick: () -> Unit,
    onTabSelected: (BottomTab) -> Unit
) {
    val records = FarmRepository.expenseRecords

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        // TOP BAR
        FarmTopBar(
            title = "Expense Records",
            showBack = true,
            onBackClick = onBack,
            rightActionIcon = "+",
            onRightActionClick = onAddExpenseClick
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            LazyColumn {
                items(records) { record ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFF3E8FF), RoundedCornerShape(10.dp))
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = when (record.category) {
                                        "Veterinary" -> "🩺"
                                        "Feed Purchase" -> "🌾"
                                        "Equipment" -> "⚙️"
                                        else -> "🚚"
                                    },
                                    fontSize = 24.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = record.category,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF222222)
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = record.amount,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF006B45)
                                )
                            }

                            Text(
                                text = record.date,
                                fontSize = 11.sp,
                                color = Color(0xFF777777)
                            )
                        }
                    }
                }
            }
        }

        // BOTTOM NAVIGATION
        FarmBottomNavBar(
            selectedTab = BottomTab.MORE,
            onTabSelected = onTabSelected
        )
    }
}
