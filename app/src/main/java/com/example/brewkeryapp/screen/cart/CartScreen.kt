
package com.example.brewkeryapp.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewkeryapp.data.model.MenuItem


// =====================================================
// CLASSIC BREWKERY COLORS
// =====================================================

private val BakeryCream = Color(0xFFF7F1E8)
private val SoftCream = Color(0xFFFFFCF7)
private val Espresso = Color(0xFF3B241B)
private val Mocha = Color(0xFF684334)
private val Caramel = Color(0xFFC58B5C)
private val SoftCaramel = Color(0xFFEBD7C2)
private val WarmBorder = Color(0xFFE5D7C7)
private val MutedText = Color(0xFF806B5E)


@Composable
fun CartScreen(
    cartItems: List<MenuItem>,
    onCheckout: () -> Unit,
    onBack: () -> Unit
) {

    val subtotal = cartItems.sumOf { it.base_price }
    val serviceCharge = if (cartItems.isNotEmpty()) subtotal * 0.05 else 0.0
    val total = subtotal + serviceCharge

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BakeryCream)
    ) {

        // =====================================================
        // TOP BAR
        // =====================================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 24.dp,
                    bottom = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(SoftCream)
                    .clickable { onBack() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "←",
                    color = Espresso,
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "YOUR ORDER",
                    color = Espresso,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Text(
                    text = "${cartItems.size} item${if (cartItems.size != 1) "s" else ""}",
                    color = MutedText,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.size(44.dp))
        }


        // =====================================================
        // EMPTY CART
        // =====================================================

        if (cartItems.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "🥐",
                        fontSize = 64.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Your basket is empty",
                        color = Espresso,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "A warm coffee or something freshly baked is waiting for you.",
                        color = MutedText,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onBack,
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Espresso
                        )
                    ) {
                        Text("Explore the menu")
                    }
                }
            }

        } else {

            // =====================================================
            // CART ITEMS
            // =====================================================

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                item {

                    Text(
                        text = "YOUR SELECTION",
                        color = Caramel,
                        fontSize = 10.sp,
                        letterSpacing = 1.5.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Freshly chosen for you",
                        color = Espresso,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }


                items(cartItems) { item ->

                    CartItemCard(item = item)
                }


                item {

                    Spacer(modifier = Modifier.height(8.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = SoftCaramel
                        )
                    ) {

                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "☕",
                                fontSize = 30.sp
                            )

                            Spacer(modifier = Modifier.size(12.dp))

                            Column {

                                Text(
                                    text = "Brewed fresh for you",
                                    color = Espresso,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )

                                Text(
                                    text = "Your order is prepared with care after confirmation.",
                                    color = Mocha,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }


            // =====================================================
            // ORDER SUMMARY
            // =====================================================

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(
                    topStart = 26.dp,
                    topEnd = 26.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = SoftCream
                )
            ) {

                Column(
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 18.dp,
                        bottom = 20.dp
                    )
                ) {

                    Text(
                        text = "ORDER SUMMARY",
                        color = Caramel,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                    SummaryRow(
                        label = "Subtotal",
                        value = "₹${subtotal.toInt()}"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    SummaryRow(
                        label = "Service charge",
                        value = "₹${serviceCharge.toInt()}"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    HorizontalDivider(color = WarmBorder)

                    Spacer(modifier = Modifier.height(14.dp))


                    SummaryRow(
                        label = "Total",
                        value = "₹${total.toInt()}",
                        isTotal = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    Button(
                        onClick = onCheckout,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Espresso
                        )
                    ) {

                        Text(
                            text = "Place order   •   ₹${total.toInt()}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}


// =====================================================
// CART ITEM CARD
// =====================================================

@Composable
private fun CartItemCard(
    item: MenuItem
) {

    val emoji = when (item.category_id.lowercase()) {
        "coffee" -> "☕"
        "bakery" -> "🥐"
        "dessert", "desserts" -> "🍰"
        else -> "☕"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = SoftCream
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(62.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(SoftCaramel),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = emoji,
                    fontSize = 30.sp
                )
            }

            Spacer(modifier = Modifier.size(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = item.name,
                    color = Espresso,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = item.tagline,
                    color = MutedText,
                    fontSize = 11.sp
                )
            }

            Text(
                text = "₹${item.base_price.toInt()}",
                color = Espresso,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


// =====================================================
// SUMMARY ROW
// =====================================================

@Composable
private fun SummaryRow(
    label: String,
    value: String,
    isTotal: Boolean = false
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            color = if (isTotal) Espresso else MutedText,
            fontSize = if (isTotal) 17.sp else 14.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )

        Text(
            text = value,
            color = Espresso,
            fontSize = if (isTotal) 19.sp else 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
