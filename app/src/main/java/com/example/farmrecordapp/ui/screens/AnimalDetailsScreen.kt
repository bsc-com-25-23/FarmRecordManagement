package com.example.farmrecordapp.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmrecordapp.model.Animal
import com.example.farmrecordapp.repository.FarmRepository
import com.example.farmrecordapp.ui.components.BottomTab
import com.example.farmrecordapp.ui.components.FarmBottomNavBar
import com.example.farmrecordapp.ui.components.FarmTopBar

@Composable
fun AnimalDetailsScreen(
    animal: Animal,
    onBack: () -> Unit,
    onTabSelected: (BottomTab) -> Unit
) {
    var selectedTab by remember { mutableStateOf("Health Records") }
    val darkGreen = Color(0xFF006B45)

    val healthRecords = FarmRepository.healthRecords.filter { it.animalName.contains(animal.type, ignoreCase = true) || it.animalId == animal.id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        // TOP BAR
        FarmTopBar(
            title = "Animal Details",
            showBack = true,
            onBackClick = onBack,
            rightActionIcon = "✏️"
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            // ANIMAL HEADER CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = getAnimalDrawableRes(animal.type)),
                        contentDescription = animal.title,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = animal.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF222222)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Box(
                            modifier = Modifier
                                .background(Color(0xFFFFE8E8), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(text = animal.type, fontSize = 11.sp, color = Color(0xFFD32F2F), fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(text = "Age: ${animal.age}", fontSize = 12.sp, color = Color(0xFF555555))
                        Text(text = "Gender: ${animal.gender}", fontSize = 12.sp, color = Color(0xFF555555))
                        Text(text = "Breed: ${animal.breed}", fontSize = 12.sp, color = Color(0xFF555555))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // SEGMENTED TAB SELECTOR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEAEFEA), RoundedCornerShape(12.dp))
                    .padding(4.dp)
            ) {
                listOf("Health Records", "Feed Records", "Notes").forEach { tab ->
                    val isSelected = tab == selectedTab
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = if (isSelected) Color.White else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedTab = tab }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tab,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) darkGreen else Color(0xFF666666)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Recent $selectedTab",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // LIST OF RECORDS
            LazyColumn {
                if (selectedTab == "Health Records") {
                    items(healthRecords) { record ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = when (record.type) {
                                        "Deworming" -> "🩺"
                                        "Vaccination" -> "💉"
                                        else -> "💊"
                                    },
                                    fontSize = 22.sp
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = record.medication, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                    Text(text = record.date, fontSize = 11.sp, color = Color(0xFF777777))
                                }
                                Text(text = "›", fontSize = 20.sp, color = Color(0xFF888888))
                            }
                        }
                    }
                } else {
                    item {
                        Text(
                            text = "No additional records found for ${animal.name}.",
                            fontSize = 13.sp,
                            color = Color(0xFF888888),
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }
            }
        }

        // BOTTOM NAVIGATION
        FarmBottomNavBar(
            selectedTab = BottomTab.ANIMALS,
            onTabSelected = onTabSelected
        )
    }
}
