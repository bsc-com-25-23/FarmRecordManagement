package com.example.farmrecordapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmrecordapp.R
import com.example.farmrecordapp.ui.components.BottomTab
import com.example.farmrecordapp.ui.components.FarmBottomNavBar
import com.example.farmrecordapp.ui.components.FarmCard
import com.example.farmrecordapp.ui.components.FarmTopBar

@Composable
fun HomeScreen(
    onNavigateToAnimals: () -> Unit,
    onNavigateToHealth: () -> Unit,
    onNavigateToFeed: () -> Unit,
    onNavigateToExpenses: () -> Unit,
    onNavigateToSales: () -> Unit,
    onNavigateToReports: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onTabSelected: (BottomTab) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        // TOP BAR
        FarmTopBar(
            title = "Farm Record Management",
            showMenu = true,
            onMenuClick = onNavigateToSettings,
            rightActionIcon = "person",
            onRightActionClick = onNavigateToSettings
        )

        // MAIN CONTENT
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // GREETING CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE5F5E4)
                ),
                border = BorderStroke(1.dp, Color(0xFFD3E7D1)),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Good Morning,",
                            fontSize = 13.sp,
                            color = Color(0xFF3B5E4F)
                        )

                        Text(
                            text = "Almerino Sikweya",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF133628)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Manage your farm records easily.",
                            fontSize = 12.sp,
                            color = Color(0xFF557265)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Image(
                        painter = painterResource(id = R.drawable.img_welcome_farm),
                        contentDescription = "Farm Banner",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(14.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // GRID ROW 1: ANIMALS & HEALTH
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                FarmCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.img_pig,
                    title = "Animals",
                    description = "View and manage\nanimal records",
                    backgroundColor = Color(0xFFFFEAEA),
                    onClick = onNavigateToAnimals
                )

                Spacer(modifier = Modifier.width(12.dp))

                FarmCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_health,
                    title = "Health",
                    description = "Vaccinations, deworming\nand treatments",
                    backgroundColor = Color(0xFFE8F1FF),
                    onClick = onNavigateToHealth
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // GRID ROW 2: FEED & EXPENSES
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                FarmCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_feed,
                    title = "Feed",
                    description = "Track feed usage\nand cost",
                    backgroundColor = Color(0xFFFFF4E5),
                    onClick = onNavigateToFeed
                )

                Spacer(modifier = Modifier.width(12.dp))

                FarmCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_expense,
                    title = "Expenses",
                    description = "Record farm\nexpenses",
                    backgroundColor = Color(0xFFF3E8FF),
                    onClick = onNavigateToExpenses
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // GRID ROW 3: SALES & REPORTS
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                FarmCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_sales,
                    title = "Sales",
                    description = "Track sales\nand income",
                    backgroundColor = Color(0xFFE6F7F0),
                    onClick = onNavigateToSales
                )

                Spacer(modifier = Modifier.width(12.dp))

                FarmCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_reports,
                    title = "Reports",
                    description = "View summaries\nand reports",
                    backgroundColor = Color(0xFFEDF2F7),
                    onClick = onNavigateToReports
                )
            }
        }

        // BOTTOM NAVIGATION BAR
        FarmBottomNavBar(
            selectedTab = BottomTab.HOME,
            onTabSelected = onTabSelected
        )
    }
}
