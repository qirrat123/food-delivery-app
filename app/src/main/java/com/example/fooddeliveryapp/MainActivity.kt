package com.example.fooddeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fooddeliveryapp.modules.authentication.login.view.LoginScreen
import com.example.fooddeliveryapp.modules.authentication.signup.view.SignupScreen
import com.example.fooddeliveryapp.modules.authentication.welcome.view.WelcomeScreen
import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fooddeliveryapp.modules.authentication.forget.view.ForgetPasswordScreen
import com.example.fooddeliveryapp.modules.authentication.reset.view.ResetPasswordScreen
import com.example.fooddeliveryapp.modules.home.menu.view.MenuScreen
import com.example.fooddeliveryapp.modules.home.product_detail.view.ProductDetailScreen
import com.example.fooddeliveryapp.modules.home.cart.view.CartScreen
import com.example.fooddeliveryapp.modules.home.checkout.view.CheckOutScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodDeliveryAppTheme {
        Greeting("Android")
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable(route = "welcome") {
            WelcomeScreen(
                onLoginClick = { navController.navigate("login") },
                onSignupClick = { navController.navigate("signup") }
            )
        }

        composable(route = "login") {
            LoginScreen(
                onSignUpClick = { navController.navigate("signup") },
                onForgetPasswordClick = { navController.navigate("forget") },
                onLoginSuccess = {
                    navController.navigate("menu") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        composable(route = "signup") {
            SignupScreen(
                onLoginClick = { navController.navigate("login") },
                onSignupSuccess = {
                    navController.navigate("menu") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        composable(route = "forget") {
            ForgetPasswordScreen(
                onContinueClick = { navController.navigate("reset_password") }
            )
        }

        composable(route = "reset_password") {
            ResetPasswordScreen(
                onResetSuccess = {
                    navController.navigate("menu") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        composable(route = "menu") {
            MenuScreen(
                onItemClick = {
                    navController.navigate("product_detail")
                }
            )
        }

        composable(route = "product_detail") {
            ProductDetailScreen(
                onBackClick = { navController.popBackStack() },
                onAddToCartClick = { navController.navigate("cart") }
            )
        }

        // 1. Cart Screen ka updated route (onCheckoutClick ke sath)
        composable(route = "cart") {
            CartScreen(
                onBackToMenuClick = { navController.navigate("menu") },
                onCancelOrderClick = { navController.navigate("product_detail") },
                onCheckoutClick = { totalAmount ->
                    // Checkout screen par navigate karein aur sath total amount bhi bhej dein
                    navController.navigate("checkout/$totalAmount")
                }
            )
        }

        // 2. Checkout Screen ka Route
        composable(route = "checkout/{totalAmount}") { backStackEntry ->
            val totalAmount = backStackEntry.arguments?.getString("totalAmount") ?: "0"

            CheckOutScreen(
                totalAmount = totalAmount,
                onBackClick = { navController.popBackStack() },
                onConfirmClick = {
                    // Jab user order confirm kare, to aap jahan bhejna chahein (e.g., Home ya Success screen)
                    navController.navigate("menu") {
                        popUpTo("menu") { inclusive = true } // Stack clear karne ke liye
                    }
                },
                onBackToMenuClick = {
                    // Menu par wapas bhejne ke liye
                    navController.navigate("menu") {
                        popUpTo("welcome") { inclusive = false }
                    }
                }
            )
        }
        }
    }
