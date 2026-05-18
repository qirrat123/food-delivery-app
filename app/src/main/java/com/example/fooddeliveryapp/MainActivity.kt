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
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.fooddeliveryapp.modules.authentication.forget.view.ForgetPasswordScreen
import com.example.fooddeliveryapp.modules.authentication.reset.view.ResetPasswordScreen
import com.example.fooddeliveryapp.modules.home.menu.view.MenuScreen
import com.example.fooddeliveryapp.modules.home.product_detail.view.ProductDetailScreen
import com.example.fooddeliveryapp.modules.home.cart.view.CartScreen
import com.example.fooddeliveryapp.modules.home.checkout.view.CheckOutScreen
// OrderTrackingScreen ka import yahan add kar diya hai
import com.example.fooddeliveryapp.modules.home.ordertracking.view.OrderTrackingScreen

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

        // Cart Screen Route
        composable(route = "cart") {
            CartScreen(
                onBackToMenuClick = { navController.navigate("menu") },
                onCancelOrderClick = { navController.navigate("product_detail") },
                onCheckoutClick = { totalAmount ->
                    // Checkout screen par navigate karte waqt string type specify kiya hai
                    navController.navigate("checkout/$totalAmount")
                }
            )
        }

        // Checkout Screen Route (Updated: onConfirmClick now links to order_tracking)
        composable(
            route = "checkout/{totalAmount}",
            arguments = listOf(
                navArgument("totalAmount") {
                    type = NavType.StringType
                    defaultValue = "0"
                }
            )
        ) { backStackEntry ->
            val totalAmount = backStackEntry.arguments?.getString("totalAmount") ?: "0"

            CheckOutScreen(
                totalAmount = totalAmount,
                onBackClick = { navController.popBackStack() },
                onConfirmClick = {
                    // Jab user confirm kare, checkout stack se remove ho jaye aur direct order_tracking par chala jaye
                    navController.navigate("order_tracking") {
                        popUpTo("menu") { inclusive = false }
                    }
                },
                onBackToMenuClick = {
                    navController.navigate("menu") {
                        popUpTo("welcome") { inclusive = false }
                    }
                }
            )
        }

        // Order Tracking Screen Route (New Route added for your Figma screen)
        composable(route = "order_tracking") {
            OrderTrackingScreen(
                onBackClick = {
                    // Back dabane par direct menu screen par clear stack ke sath le jaye
                    navController.navigate("menu") {
                        popUpTo("menu") { inclusive = true }
                    }
                }
            )
        }
    }
}