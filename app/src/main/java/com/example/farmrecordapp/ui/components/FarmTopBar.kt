package com.example.farmrecordapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FarmTopBar(
    title: String,
    showBack: Boolean = false,
    onBackClick: () -> Unit = {},
    showMenu: Boolean = false,
    onMenuClick: () -> Unit = {},
    rightActionIcon: String? = null,
    onRightActionClick: () -> Unit = {}
) {
    val darkGreen = Color(0xFF005C3B)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(darkGreen)
            .statusBarsPadding()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showMenu) {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Navigation Menu",
                    tint = Color.White
                )
            }
        } else if (showBack) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go Back",
                    tint = Color.White
                )
            }
        }

        Text(
            text = title,
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier
                .weight(1f)
                .padding(start = if (showMenu || showBack) 4.dp else 16.dp)
        )

        if (rightActionIcon != null) {
            val vectorIcon: ImageVector = when (rightActionIcon) {
                "+", "add" -> Icons.Default.Add
                "👤", "profile", "person" -> Icons.Default.Person
                "✏️", "edit" -> Icons.Default.Edit
                else -> Icons.Default.MoreVert
            }

            IconButton(onClick = onRightActionClick) {
                Icon(
                    imageVector = vectorIcon,
                    contentDescription = "Action",
                    tint = Color.White
                )
            }
        }
    }
}
