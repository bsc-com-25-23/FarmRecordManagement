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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmrecordapp.ui.components.BottomTab
import com.example.farmrecordapp.ui.components.FarmBottomNavBar
import com.example.farmrecordapp.ui.components.FarmTopBar

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onTabSelected: (BottomTab) -> Unit,
    onNavigateToExpenses: () -> Unit,
    onNavigateToSales: () -> Unit,
    onNavigateToReports: () -> Unit
) {
    val darkGreen = Color(0xFF006B45)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        // TOP BAR
        FarmTopBar(
            title = "Settings & More",
            showBack = true,
            onBackClick = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // QUICK MODULES
            Text(
                text = "Quick Access",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
            Spacer(modifier = Modifier.height(8.dp))

            SettingsOptionItem(icon = "💰", title = "Expenses Records", onClick = onNavigateToExpenses)
            Spacer(modifier = Modifier.height(8.dp))
            SettingsOptionItem(icon = "📊", title = "Sales Records", onClick = onNavigateToSales)
            Spacer(modifier = Modifier.height(8.dp))
            SettingsOptionItem(icon = "📋", title = "Reports & Analytics", onClick = onNavigateToReports)

            Spacer(modifier = Modifier.height(20.dp))

            // GENERAL SETTINGS
            Text(
                text = "Preferences & Account",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
            Spacer(modifier = Modifier.height(8.dp))

            SettingsOptionItem(icon = "👤", title = "Profile")
            Spacer(modifier = Modifier.height(8.dp))
            SettingsOptionItem(icon = "🔒", title = "Change Password")
            Spacer(modifier = Modifier.height(8.dp))
            SettingsOptionItem(icon = "☁️", title = "Backup Data")
            Spacer(modifier = Modifier.height(8.dp))
            SettingsOptionItem(icon = "ℹ️", title = "About App")

            Spacer(modifier = Modifier.height(24.dp))

            // BRANDING FOOTER CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "🏡 🌾", fontSize = 36.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Farm Record Management",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkGreen
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Better records • Healthier animals • Higher profits",
                        fontSize = 12.sp,
                        color = Color(0xFF555555),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "v1.0.0",
                        fontSize = 11.sp,
                        color = Color(0xFF888888)
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
fun SettingsOptionItem(
    icon: String,
    title: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
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
                    .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text(text = icon, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF222222),
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "›",
                fontSize = 22.sp,
                color = Color(0xFF888888)
            )
        }
    }
}
