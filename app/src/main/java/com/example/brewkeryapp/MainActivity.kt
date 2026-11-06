package com.example.brewkeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.brewkeryapp.data.model.MenuItem
import com.example.brewkeryapp.screen.cart.CartScreen
import com.example.brewkeryapp.screen.detail.DetailScreen
import com.example.brewkeryapp.screen.menu.MenuScreen
import com.example.brewkeryapp.screen.order.OrderScreen
import com.example.brewkeryapp.ui.theme.BakeryAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BakeryAppTheme {

                var currentScreen by remember {
                    mutableStateOf("menu")
                }

                var selectedItem by remember {
                    mutableStateOf<MenuItem?>(null)
                }

                val cartItems = remember {
                    mutableStateListOf<MenuItem>()
                }

                when (currentScreen) {

                    // MENU SCREEN
                    "menu" -> {
                        MenuScreen(
                            onItemClick = { item ->
                                selectedItem = item
                                currentScreen = "detail"
                            },

                            onCartClick = {
                                currentScreen = "cart"
                            },

                            cartCount = cartItems.size
                        )
                    }

                    // DETAIL SCREEN
                    "detail" -> {
                        selectedItem?.let { item ->

                            DetailScreen(
                                itemId = item.id,

                                onAddToCart = { itemFromApi ->
                                    cartItems.add(itemFromApi)
                                    currentScreen = "cart"
                                },

                                onBack = {
                                    currentScreen = "menu"
                                }
                            )
                        }
                    }

                    // CART SCREEN
                    "cart" -> {
                        CartScreen(
                            cartItems = cartItems,

                            onCheckout = {
                                currentScreen = "order"
                            },

                            onBack = {
                                currentScreen = "menu"
                            }
                        )
                    }

                    // ORDER SCREEN
                    "order" -> {
                        OrderScreen(
                            onContinueShopping = {
                                cartItems.clear()
                                currentScreen = "menu"
                            }
                        )
                    }
                }
            }
        }
    }
}