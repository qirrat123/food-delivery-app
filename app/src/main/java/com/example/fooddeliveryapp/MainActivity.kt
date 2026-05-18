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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Line 21 ko aise badlein:
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
        startDestination = "welcome" // Ya jo bhi aapki pehli screen hai
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
                // Yeh line add karein:
                onLoginSuccess = {
                    navController.navigate("menu") {
                        popUpTo("welcome") {
                            inclusive = true
                        } // Login ke baad piche welcome par na jaye
                    }
                }
            )
        }

        composable(route = "signup") {
            SignupScreen(
                onLoginClick = {
                    navController.navigate("login")
                },
                onSignupSuccess = {
                    // Account banne ke baad seedha Menu par bhejein
                    navController.navigate("menu") {
                        // Taake back button dabane par wapas signup form na aaye
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        // Line 79: Forget Password ka route yahan shuru hota hai
        composable(route = "forget") {
            ForgetPasswordScreen(
                onContinueClick = { navController.navigate("reset_password") }
            )
        } // <--- Yeh bracket line 81 par khatam ho raha hai

        // YAHAN LIKHEIN (Line 82 par purana code delete karke ye naya likhein)
        composable(route = "reset_password") {
            ResetPasswordScreen(
                onResetSuccess = {
                    navController.navigate("menu") {
                        // Poore pichle stack ko clear kar dein taake user wapas reset page par na ja sakay
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }
        composable(route = "menu") {
            MenuScreen(
                onItemClick = {
                    navController.navigate("product_detail") // Menu se Detail page par jane ke liye
                }
            )
        }

        // 1. Product Detail Screen ka Route
        composable(route = "product_detail") {
            ProductDetailScreen(
                onBackClick = { navController.popBackStack() }, // Wapas pichli screen par jaane ke liye
                onAddToCartClick = { navController.navigate("cart") } // Cart screen par bhejne ke liye
            )
        }

// 2. Cart Screen ka Route
        composable(route = "cart") {
            CartScreen(
                onBackToMenuClick = { navController.navigate("menu") },
                onCancelOrderClick = { navController.navigate("product_detail") } // Cancel par wapas Product Detail bheje
            )
        }
                }

        }



