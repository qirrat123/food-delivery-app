package com.example.fooddeliveryapp.modules.home.checkout.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckOutScreen(
    onBackClick: () -> Unit,
    onBackToMenuClick: () -> Unit,
    totalAmount: String,
    onConfirmClick: () -> Unit
) {
    // State for selecting Payment Method
    var isCardSelected by remember { mutableStateOf(true) }

    // Safely parse totalAmount to double/int for calculation, default to 0.0 if empty or invalid
    val itemsTotal = totalAmount.toDoubleOrNull() ?: 0.0
    val deliveryCharges = 200.0
    val netTotal = itemsTotal + deliveryCharges

    Scaffold(
        containerColor = Color(0xFFE5D9C3) // Exact Figma Background Color
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Top Header: Back Arrow and Title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onBackClick() },
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Check Out",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Delivery Address Section
            Text(
                text = "Delivery Address:",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Address Container Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C8AF).copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Home",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "123, gulberg lahore",
                            fontSize = 13.sp,
                            color = Color.DarkGray
                        )
                    }
                    Text(
                        text = "change",
                        fontSize = 14.sp,
                        color = Color(0xFFD59200), // Figma accent orange
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { /* Address change logic */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Payment Method Section Heading
            Text(
                text = "Payment Method",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Option 1: Credit Card / Debit Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isCardSelected = true },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD6C8AF).copy(alpha = 0.5f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Custom Checkbox/Radio circle
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .border(1.5.dp, Color.Black, RoundedCornerShape(4.dp))
                            .background(
                                if (isCardSelected) Color(0xFFF2A900) else Color.Transparent,
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_background), // Apni payment/card icon drawable replace karein
                        contentDescription = "Card",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "credit card/debit card",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Option 2: Cash on Delivery Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isCardSelected = false },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD6C8AF).copy(alpha = 0.5f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Custom Checkbox/Radio square box
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .border(1.5.dp, Color.Black, RoundedCornerShape(4.dp))
                                .background(
                                    if (!isCardSelected) Color(0xFFF2A900) else Color.Transparent,
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Cash on delivery",
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }

                    // Orange dot layout right indicator
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .background(Color(0xFFF2A900), shape = CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Order Summary Section Heading
            Text(
                text = "Order Summary",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Items Total Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Items total", fontSize = 14.sp, color = Color.Black)
                Text(
                    text = "Rs ${String.format("%.0f", itemsTotal)}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Delivery Charges Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Delivery charges", fontSize = 14.sp, color = Color.Black)
                Text(
                    text = "Rs ${String.format("%.0f", deliveryCharges)}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Net Total Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Total:", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(
                    text = "Rs ${String.format("%.0f", netTotal)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Confirm Action Button
            Button(
                onClick = { onConfirmClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2A900)) // Exact Orange/Yellow
            ) {
                Text(
                    text = "Confirm",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Back To Menu Custom Text Link
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Back To Menu",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier
                        .clickable { onBackToMenuClick() }
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}