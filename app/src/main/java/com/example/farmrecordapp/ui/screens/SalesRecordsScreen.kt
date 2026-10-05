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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun SalesRecordsScreen(
    onBack: () -> Unit,
    onAddSaleClick: () -> Unit,
    onTabSelected: (BottomTab) -> Unit
) {
    var selectedFilter by remember { mutableStateOf("All") }
    val filters = listOf("All", "Live Animals", "Products")
    val darkGreen = Color(0xFF006B45)

    val records = FarmRepository.saleRecords.filter {
        if (selectedFilter == "All") true
        else it.category.equals(selectedFilter, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        // TOP BAR
        FarmTopBar(
            title = "Sales Records",
            showBack = true,
            onBackClick = onBack,
            rightActionIcon = "+",
            onRightActionClick = onAddSaleClick
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // FILTER CHIPS
            LazyRow {
                items(filters) { filter ->
                    val isSelected = filter == selectedFilter
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .background(
                                color = if (isSelected) darkGreen else Color(0xFFE8EFEA),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable { selectedFilter = filter }
                            .padding(horizontal = 18.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = filter,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.White else Color(0xFF333333)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // LIST OF SALES
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
                                    .background(Color(0xFFE6F7F0), RoundedCornerShape(10.dp))
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = when {
                                        record.item.contains("Pig", true) -> "🐖"
                                        record.item.contains("Chicken", true) -> "🐔"
                                        record.item.contains("Egg", true) -> "🥚"
                                        else -> "🐐"
                                    },
                                    fontSize = 24.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = record.item,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF222222)
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = record.amount,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = darkGreen
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
