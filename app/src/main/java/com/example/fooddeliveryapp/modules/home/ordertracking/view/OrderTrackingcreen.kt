package com.example.fooddeliveryapp.modules.home.ordertracking.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderTrackingScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        containerColor = Color(0xFFE5D9C3), // Exact Figma Background Color
        bottomBar = {
            // Figma Bottom Navigation Bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFE89005) // Exact Bottom Orange Strip
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Search/Home/Cart Icons structure placeholders
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_search),
                        contentDescription = "Search",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_compass),
                        contentDescription = "Home",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_input_add),
                        contentDescription = "Cart",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // 1. Header Row (Back arrow and Title)
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
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Order Tracking",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.offset(x = (-10).dp) // Center align correction
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 2. Arriving Card Details
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDCD1B9)) // Accurate darker tint card
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "ORDER # 1234567890",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "Arriving in ",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "25 mins",
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Your order is on the way",
                        fontSize = 13.sp,
                        color = Color.DarkGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // 3. Custom Horizontal Status Stepper Workflow
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Step 1: Order Confirmed
                StatusStepItem(
                    title = "order confirmed",
                    time = "9:00 pm",
                    isActive = true,
                    isCompleted = true,
                    modifier = Modifier.weight(1f)
                )

                // Step 2: Preparation
                StatusStepItem(
                    title = "Preparation",
                    time = "9:15 pm",
                    isActive = true,
                    isCompleted = true,
                    modifier = Modifier.weight(1f)
                )

                // Step 3: Out for Delivery (Current Active Icon State)
                StatusStepItem(
                    title = "out for delivery",
                    time = "9:30 pm",
                    isActive = true,
                    isCompleted = false,
                    isRiderIcon = true,
                    modifier = Modifier.weight(1f)
                )

                // Step 4: Delivered
                StatusStepItem(
                    title = "Delivered",
                    time = ".....",
                    isActive = false,
                    isCompleted = false,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 4. Rider Information Section Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDCD1B9).copy(alpha = 0.6f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Rider Profile Image Circle Layout
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background), // Apni rider image resource attach karein
                        contentDescription = "Rider Photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Rider Metadata Text Info
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Abdullah kareem",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "Your rider",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "4.8",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    // Message Action Icon
                    Icon(
                        imageVector = Icons.Default.ChatBubbleOutline,
                        contentDescription = "Chat with rider",
                        tint = Color(0xFFD59200), // Accent orange color
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { /* Handle Chat Logic */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 5. Estimated Delivery Banner Bottom Alert
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFCBEAD7)) // Light green accent figma color
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_my_calendar),
                        contentDescription = "Clock",
                        tint = Color(0xFF1E5E3A),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Estimated Delivery : ",
                        fontSize = 13.sp,
                        color = Color(0xFF1E5E3A),
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "9:55 AM - 10:15 AM",
                        fontSize = 13.sp,
                        color = Color(0xFF1E5E3A),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun StatusStepItem(
    title: String,
    time: String,
    isActive: Boolean,
    isCompleted: Boolean,
    isRiderIcon: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Step Icon Circle
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(
                    if (isActive) Color(0xFF32A02C) else Color(0xFFBFB39C) // Active Green vs Inactive Gray-sand
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isRiderIcon) {
                Icon(
                    imageVector = Icons.Default.DeliveryDining,
                    contentDescription = "Rider",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            } else if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Done",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Step Label Text
        Text(
            text = title,
            fontSize = 11.sp,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
            color = if (isActive) Color.Black else Color.Gray,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Step Timestamp Detail Text
        Text(
            text = time,
            fontSize = 10.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}