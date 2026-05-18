package com.example.fooddeliveryapp.modules.home.menu.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
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
import com.example.fooddeliveryapp.R // Apna sahi package name check kar lein

// --- Data Model ---
data class FoodItem(
    val name: String,
    val price: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(onItemClick: () -> Unit) {
    Scaffold(
        bottomBar = { CustomBottomBar() },
        containerColor = Color(0xFFF9F9F9) // Figma background color
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // 1. Header Section
            HeaderSection()

            // 2. Search Bar
            SearchBarSection()

            // 3. Pizza Hero Banner
            PizzaHeroBanner()

            // 4. Categories Icons
            CategoriesSection()

            // 5. Promotion Section
            Text(
                text = "Promotion",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            PromotionBanner()

            // 6. Popular Section (Exactly like Figma)
            Text(
                text = "Popluar",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // --- IS PURANI ROW KO DELETE KARKE NAYI WALI YE LIKHEIN ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Beef Burger Card
                Box(modifier = Modifier.clickable { onItemClick() }) {
                    FoodCard(FoodItem("Beef Burger", "Rs 670", R.drawable.beef_burger))
                }

                // Pizza Card
                Box(modifier = Modifier.clickable { onItemClick() }) {
                    FoodCard(FoodItem("Cheesy Pizza", "Rs 1590", R.drawable.popular_pizza))
                }
            }
            // -------------------------------------------------------

            // Extra spacing for bottom
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Menu, contentDescription = "Menu", modifier = Modifier.size(24.dp))
        Text(
            text = "MENU",
            fontWeight = FontWeight.Black,
            fontSize = 20.sp,
            letterSpacing = 2.sp
        )
        Icon(Icons.Default.Mic, contentDescription = "Voice Search", modifier = Modifier.size(24.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarSection() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Search \"pizza\"", color = Color.Gray) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(30.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedBorderColor = Color(0xFFE0E0E0),
            focusedBorderColor = Color.Gray
        )
    )
}

@Composable
fun PizzaHeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.pizza_banner), // Make sure image exists
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun CategoriesSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CategoryIconItem("View all", R.drawable.view_all)
        CategoryIconItem("burger", R.drawable.burger_category)
        CategoryIconItem("pizza", R.drawable.pizza_category)
        CategoryIconItem("pasta", R.drawable.pasta_category)
    }
}

@Composable
fun CategoryIconItem(label: String, iconRes: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.size(65.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.padding(8.dp).fillMaxSize()
            )
        }
        Text(text = label, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun PromotionBanner() {
    Image(
        painter = painterResource(id = R.drawable.promo_burger),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun FoodCard(item: FoodItem) {
    Card(
        modifier = Modifier
            .width(165.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = item.price, color = Color.Gray, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(8.dp))

            // Green Add Button
            Surface(
                modifier = Modifier.size(26.dp).clickable { },
                shape = CircleShape,
                color = Color(0xFF4CAF50)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Composable
fun CustomBottomBar() {
    Surface(
        color = Color(0xFFF5A623), // Figma Orange
        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        modifier = Modifier.fillMaxWidth().height(65.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(30.dp))
            Icon(Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(30.dp))
        }
    }
}
