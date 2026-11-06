
package com.example.brewkeryapp.screen.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.brewkeryapp.data.model.MenuItem
import com.example.brewkeryapp.viewmodel.MenuViewModel


// =====================================================
// CLASSIC BREWKERY COLOR PALETTE
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
fun MenuScreen(
    onItemClick: (MenuItem) -> Unit,
    onCartClick: () -> Unit,
    cartCount: Int,
    viewModel: MenuViewModel = viewModel()
) {

    val menuItems by viewModel.items.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BakeryCream)
    ) {

        // =====================================================
        // TOP HEADER
        // =====================================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 22.dp,
                    end = 22.dp,
                    top = 28.dp,
                    bottom = 18.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = "THE",
                    color = Caramel,
                    fontSize = 10.sp,
                    letterSpacing = 4.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "BREWKERY",
                    color = Espresso,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    fontFamily = FontFamily.Serif
                )

                Text(
                    text = "COFFEE • BAKERY • MOMENTS",
                    color = MutedText,
                    fontSize = 9.sp,
                    letterSpacing = 1.5.sp
                )
            }


            // CART BUTTON

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Espresso)
                    .clickable { onCartClick() },
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "🛒",
                    fontSize = 20.sp
                )

                if (cartCount > 0) {

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(Caramel),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = cartCount.toString(),
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }


        // =====================================================
        // HERO SECTION
        // =====================================================

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(
                containerColor = Espresso
            )
        ) {

            Column(
                modifier = Modifier.padding(
                    horizontal = 22.dp,
                    vertical = 24.dp
                )
            ) {

                Text(
                    text = "YOUR DAILY RITUAL",
                    color = SoftCaramel,
                    fontSize = 10.sp,
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Freshly brewed.\nWarmly baked.",
                    color = Color.White,
                    fontSize = 28.sp,
                    lineHeight = 34.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Thoughtfully crafted coffee and bakery favourites, made for slow mornings and good conversations.",
                    color = Color(0xFFEADDD2),
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "☕  Fresh every day    •    🥐  Made with care",
                    color = SoftCaramel,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }


        Spacer(modifier = Modifier.height(24.dp))


        // =====================================================
        // MENU TITLE
        // =====================================================

        Column(
            modifier = Modifier.padding(horizontal = 22.dp)
        ) {

            Text(
                text = "OUR MENU",
                color = Caramel,
                fontSize = 10.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Today's favourites",
                color = Espresso,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(
                color = WarmBorder,
                thickness = 1.dp
            )
        }


        Spacer(modifier = Modifier.height(12.dp))


        // =====================================================
        // API STATES
        // =====================================================

        when {

            // LOADING

            isLoading -> {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "☕",
                            fontSize = 42.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Preparing today's menu...",
                            color = Mocha,
                            fontSize = 15.sp
                        )
                    }
                }
            }


            // ERROR

            error != null -> {

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
                            text = "Something went wrong",
                            color = Espresso,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "We couldn't load today's menu.",
                            color = MutedText,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = error ?: "",
                            color = Color.Red,
                            fontSize = 11.sp
                        )
                    }
                }
            }


            // SUCCESS

            else -> {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 24.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    items(menuItems) { item ->

                        PremiumMenuItemCard(
                            item = item,
                            onClick = {
                                onItemClick(item)
                            }
                        )
                    }
                }
            }
        }
    }
}


// =====================================================
// PREMIUM MENU ITEM CARD
// =====================================================

@Composable
private fun PremiumMenuItemCard(
    item: MenuItem,
    onClick: () -> Unit
) {

    val productEmoji = when (item.category_id.lowercase()) {
        "coffee" -> "☕"
        "bakery" -> "🥐"
        "dessert", "desserts" -> "🍰"
        else -> "☕"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = SoftCream
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // PRODUCT VISUAL

            Box(
                modifier = Modifier
                    .size(82.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(SoftCaramel),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = productEmoji,
                    fontSize = 38.sp
                )
            }


            Spacer(modifier = Modifier.width(16.dp))


            // PRODUCT DETAILS

            Column(
                modifier = Modifier.weight(1f)
            ) {

                if (item.badge.isNotEmpty()) {

                    Text(
                        text = item.badge.uppercase(),
                        color = Caramel,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                }


                Text(
                    text = item.name,
                    color = Espresso,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )


                Spacer(modifier = Modifier.height(4.dp))


                Text(
                    text = item.tagline,
                    color = MutedText,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )


                Spacer(modifier = Modifier.height(10.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "₹${item.base_price.toInt()}",
                        color = Espresso,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )


                    Text(
                        text = "★ ${item.rating}",
                        color = Mocha,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
