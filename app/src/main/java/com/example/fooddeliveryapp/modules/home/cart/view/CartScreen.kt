package com.example.fooddeliveryapp.modules.home.cart.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
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

// 1. Cart Item ke liye Ek Data Class banayi taake state dynamic manage ho sake
data class CartItem(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val unitPrice: Int, // Integer mein price rakha taake Calculation dynamic ho sake
    var quantity: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onBackToMenuClick: () -> Unit,
    onCancelOrderClick: () -> Unit,
    onCheckoutClick: (totalAmount: Int) -> Unit
) {
    // 2. Initial Dynamic List state mein maintain ki
    var cartItemsList by remember {
        mutableStateOf(
            listOf(
                CartItem(id = 1, imageRes = R.drawable.beef_burger, title = "Beef Burger", unitPrice = 670, quantity = 1),
                CartItem(id = 2, imageRes = R.drawable.pasta_plate, title = "Pasta", unitPrice = 1990, quantity = 1)
            )
        )
    }

    // --- YAHAN MAIN STATE ADD KI HAI ---
    var instructionText by remember { mutableStateOf("") }

    // 3. Dynamic Total Calculation: Price * Quantity ka sum
    val currentTotal = cartItemsList.sumOf { it.unitPrice * it.quantity }

    Scaffold(
        containerColor = Color(0xFFE5D9C3)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Back Arrow Icon
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onBackToMenuClick() },
                tint = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Screen Title (Dynamic Count)
            Text(
                text = "${cartItemsList.size} items in a cart",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Dynamic Cart Items Render
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                cartItemsList.forEach { item ->
                    CartItemRow(
                        imageRes = item.imageRes,
                        title = item.title,
                        price = "Rs ${item.unitPrice}",
                        quantity = "${item.quantity}",
                        onIncreaseClick = {
                            cartItemsList = cartItemsList.map {
                                if (it.id == item.id) it.copy(quantity = it.quantity + 1) else it
                            }
                        },
                        onDecreaseClick = {
                            cartItemsList = cartItemsList.map {
                                if (it.id == item.id && it.quantity > 1) it.copy(quantity = it.quantity - 1) else it
                            }
                        },
                        onRemoveClick = {
                            cartItemsList = cartItemsList.filter { it.id != item.id }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Order Instruction Section
            Text(
                text = "Order Instruction",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // --- YAHAN PURANA BOX HATA KAR INPUT FIELD LAGA DIYA HAI ---
            OutlinedTextField(
                value = instructionText,
                onValueChange = { instructionText = it },
                placeholder = {
                    Text(
                        text = "Write your instructions here...",
                        color = Color.Black.copy(alpha = 0.4f),
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFD6C8AF),
                    unfocusedContainerColor = Color(0xFFD6C8AF),
                    focusedBorderColor = Color.Black.copy(alpha = 0.4f),
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Total Price Section (Dynamic Total Text)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Rs ${String.format("%,d", currentTotal)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action Buttons
            Button(
                onClick = {onCheckoutClick(currentTotal) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2A900))
            ) {
                Text("Check Out", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onCancelOrderClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD59200))
            ) {
                Text("Cancel Order", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Back To Menu Custom Text Button Link
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
        }
    }
}

@Composable
fun CartItemRow(
    imageRes: Int,
    title: String,
    price: String,
    quantity: String,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = title,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = price, fontSize = 12.sp, color = Color.DarkGray)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color.Black, shape = CircleShape)
                            .clickable { onDecreaseClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "—",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = quantity,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color.Black, shape = CircleShape)
                            .clickable { onIncreaseClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .size(24.dp)
                .border(1.dp, Color.Black, CircleShape)
                .clickable { onRemoveClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                modifier = Modifier.size(14.dp),
                tint = Color.Black
            )
        }
    }
}