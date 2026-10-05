package com.example.farmrecordapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.farmrecordapp.ui.components.SalesExpensesChart

@Composable
fun ReportsScreen(
    onBack: () -> Unit,
    onTabSelected: (BottomTab) -> Unit
) {
    var selectedPeriod by remember { mutableStateOf("This Month") }
    val darkGreen = Color(0xFF006B45)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        // TOP BAR
        FarmTopBar(
            title = "Reports",
            showBack = true,
            onBackClick = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // PERIOD SELECTOR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8EFEA), RoundedCornerShape(12.dp))
                    .padding(4.dp)
            ) {
                listOf("This Month", "This Year").forEach { period ->
                    val isSelected = period == selectedPeriod
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = if (isSelected) darkGreen else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedPeriod = period }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = period,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.White else Color(0xFF555555)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2x2 METRIC CARDS
            Row(modifier = Modifier.fillMaxWidth()) {
                MetricCard(
                    modifier = Modifier.weight(1f),
                    icon = "🧭",
                    title = "Total Animals",
                    value = "${FarmRepository.animals.size}",
                    bgColor = Color(0xFFEBF3FF)
                )

                Spacer(modifier = Modifier.width(12.dp))

                MetricCard(
                    modifier = Modifier.weight(1f),
                    icon = "💰",
                    title = "Total Sales",
                    value = "MK 92,000",
                    bgColor = Color(0xFFEBF9F3)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                MetricCard(
                    modifier = Modifier.weight(1f),
                    icon = "🪙",
                    title = "Total Expenses",
                    value = "MK 45,000",
                    bgColor = Color(0xFFFFEAEA)
                )

                Spacer(modifier = Modifier.width(12.dp))

                MetricCard(
                    modifier = Modifier.weight(1f),
                    icon = "📈",
                    title = "Net Profit",
                    value = "MK 47,000",
                    bgColor = Color(0xFFE8F5E9)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // BAR CHART CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    SalesExpensesChart(
                        salesAmount = 92000f,
                        expenseAmount = 45000f
                    )
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

@Composable
fun MetricCard(
    modifier: Modifier = Modifier,
    icon: String,
    title: String,
    value: String,
    bgColor: Color
) {
    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = icon, fontSize = 22.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, fontSize = 12.sp, color = Color(0xFF555555))
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF222222))
        }
    }
}
