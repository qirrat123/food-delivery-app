package com.example.fooddeliveryapp.modules.home.product_detail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R

@Composable
fun ProductDetailScreen(onBackClick: () -> Unit,
                        onAddToCartClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            // Figma Background Color
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 1. Top Bar (Back Button)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
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
            }

            // 2. Main Product Image
            Image(
                painter = painterResource(id = R.drawable.beef_burger),
                contentDescription = "Beef Burger",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .padding(horizontal = 20.dp),
                contentScale = ContentScale.Fit
            )

            // 3. Details Container
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                color = Color(0xFFFFE5B4).copy(alpha = 0.7f) // Slightly transparent card
            ) {
                Column(
                    modifier = Modifier.padding(30.dp)
                ) {
                    // Rating and Price Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Rating Badge
                        Surface(
                            color = Color(0xFFFDC95E),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Star, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("4.8", fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }

                        Text(
                            text = "Rs 670",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Beef Burger",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        text = "Add Ons",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // Add Ons List
                    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                        AddOnItem("Extra Tomato", R.drawable.tomatos) // Replace with tomato icon
                        AddOnItem("Extra Cheese", R.drawable.cheese) // Replace with cheese icon
                        AddOnItem("Extra leaf lettuce", R.drawable.lettuce) // Replace with lettuce icon
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // 4. Add To Cart Button
                    Button(
                        onClick = { onAddToCartClick() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
                    ) {
                        Text("Add To Cart", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Spacer(modifier = Modifier.height(50.dp))   }
                }
            }
        }
    }
}

@Composable
fun AddOnItem(name: String, iconRes: Int) {
    var count by remember { mutableIntStateOf(1) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = name, fontSize = 14.sp, color = Color.Black)
        }

        // Stepper
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_add), // Placeholder for +
                contentDescription = null,
                modifier = Modifier.size(20.dp).clickable { count++ }
            )
            Text(text = "$count", modifier = Modifier.padding(horizontal = 8.dp), fontWeight = FontWeight.Bold)
            Icon(
                painter = painterResource(id = android.R.drawable.ic_delete), // Placeholder for -
                contentDescription = null,
                modifier = Modifier.size(20.dp).clickable { if(count > 0) count-- }
            )
        }
    }
}