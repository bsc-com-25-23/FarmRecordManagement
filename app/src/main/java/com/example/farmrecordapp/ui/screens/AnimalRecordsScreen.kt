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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.farmrecordapp.R
import com.example.farmrecordapp.model.Animal
import com.example.farmrecordapp.repository.FarmRepository
import com.example.farmrecordapp.ui.components.BottomTab
import com.example.farmrecordapp.ui.components.FarmBottomNavBar
import com.example.farmrecordapp.ui.components.FarmTopBar

fun getAnimalDrawableRes(type: String): Int {
    return when (type.lowercase()) {
        "pig" -> R.drawable.img_pig
        "goat" -> R.drawable.img_goat
        "cow" -> R.drawable.img_cow
        "chicken" -> R.drawable.img_chicken
        else -> R.drawable.img_pig
    }
}

@Composable
fun AnimalRecordsScreen(
    onBack: () -> Unit,
    onAddAnimalClick: () -> Unit,
    onAnimalClick: (Animal) -> Unit,
    onTabSelected: (BottomTab) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All", "Pigs", "Goats", "Cows", "Chickens")

    val darkGreen = Color(0xFF006B45)

    val filteredAnimals = FarmRepository.animals.filter { animal ->
        val matchesCategory = when (selectedCategory) {
            "Pigs" -> animal.type.equals("Pig", ignoreCase = true)
            "Goats" -> animal.type.equals("Goat", ignoreCase = true)
            "Cows" -> animal.type.equals("Cow", ignoreCase = true)
            "Chickens" -> animal.type.equals("Chicken", ignoreCase = true)
            else -> true
        }
        val matchesSearch = animal.title.contains(searchQuery, ignoreCase = true) ||
                animal.type.contains(searchQuery, ignoreCase = true) ||
                animal.name.contains(searchQuery, ignoreCase = true)
        matchesCategory && matchesSearch
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF7))
    ) {
        // TOP BAR
        FarmTopBar(
            title = "Animal Records",
            showBack = true,
            onBackClick = onBack,
            rightActionIcon = "+",
            onRightActionClick = onAddAnimalClick
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // SEARCH BAR
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("🔍 Search animals...", color = Color(0xFF888888), fontSize = 14.sp) },
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = darkGreen,
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // CATEGORY FILTER CHIPS
            LazyRow {
                items(categories) { category ->
                    val isSelected = category == selectedCategory
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .background(
                                color = if (isSelected) darkGreen else Color(0xFFE8EFEA),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable { selectedCategory = category }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = category,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.White else Color(0xFF333333)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ANIMAL LIST
            LazyColumn {
                items(filteredAnimals) { animal ->
                    AnimalListItem(
                        animal = animal,
                        onClick = { onAnimalClick(animal) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
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

@Composable
fun AnimalListItem(
    animal: Animal,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Animal Photo Thumbnail
            Image(
                painter = painterResource(id = getAnimalDrawableRes(animal.type)),
                contentDescription = animal.title,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = animal.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF222222)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${animal.type} • ${animal.age}",
                    fontSize = 12.sp,
                    color = Color(0xFF666666)
                )
            }

            Text(
                text = "›",
                fontSize = 24.sp,
                color = Color(0xFF888888)
            )
        }
    }
}
