package com.example.farmrecordapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SalesExpensesChart(
    salesAmount: Float = 92000f,
    expenseAmount: Float = 45000f
) {
    val greenColor = Color(0xFF006B45)
    val orangeColor = Color(0xFFE08B00)
    val gridColor = Color(0xFFE0E0E0)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sales vs Expenses",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF222222),
                modifier = Modifier.weight(1f)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Canvas(modifier = Modifier.width(10.dp).height(10.dp)) {
                    drawCircle(color = greenColor)
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Sales", fontSize = 11.sp, color = Color(0xFF555555))
            }

            Spacer(modifier = Modifier.width(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Canvas(modifier = Modifier.width(10.dp).height(10.dp)) {
                    drawCircle(color = orangeColor)
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Expenses", fontSize = 11.sp, color = Color(0xFF555555))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            val width = size.width
            val height = size.height

            // Draw Y grid lines
            val maxVal = 120000f
            val steps = listOf(120000, 90000, 60000, 30000, 0)
            steps.forEachIndexed { index, _ ->
                val y = index * (height / (steps.size - 1))
                drawLine(
                    color = gridColor,
                    start = Offset(0f, y),
                    end = Offset(width, y),
                    strokeWidth = 1f
                )
            }

            // Draw Bars
            val barWidth = 32.dp.toPx()
            val spaceBetween = 12.dp.toPx()
            val groupWidth = (barWidth * 2) + spaceBetween
            val startX = (width - groupWidth) / 2

            val salesHeight = (salesAmount / maxVal) * height
            val expenseHeight = (expenseAmount / maxVal) * height

            // Sales bar
            drawRoundRect(
                color = greenColor,
                topLeft = Offset(startX, height - salesHeight),
                size = Size(barWidth, salesHeight),
                cornerRadius = CornerRadius(8f, 8f)
            )

            // Expenses bar
            drawRoundRect(
                color = orangeColor,
                topLeft = Offset(startX + barWidth + spaceBetween, height - expenseHeight),
                size = Size(barWidth, expenseHeight),
                cornerRadius = CornerRadius(8f, 8f)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "This Month",
            fontSize = 11.sp,
            color = Color(0xFF777777),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
