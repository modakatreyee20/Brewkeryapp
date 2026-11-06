
package com.example.brewkeryapp.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.brewkeryapp.data.model.MenuItem
import com.example.brewkeryapp.viewmodel.DetailViewModel


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
fun DetailScreen(
    itemId: Int,
    onAddToCart: (MenuItem) -> Unit,
    onBack: () -> Unit,
    viewModel: DetailViewModel = viewModel()
) {

    val item by viewModel.item.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(itemId) {
        viewModel.loadItem(itemId)
    }

    when {

        // ================= LOADING =================

        isLoading -> {
            BakeryLoadingScreen()
        }


        // ================= ERROR =================

        error != null -> {
            BakeryErrorScreen(
                error = error ?: "Something went wrong",
                onBack = onBack
            )
        }


        // ================= SUCCESS =================

        item != null -> {
            DetailContent(
                item = item!!,
                onAddToCart = onAddToCart,
                onBack = onBack
            )
        }
    }
}


// =====================================================
// LOADING SCREEN
// =====================================================

@Composable
private fun BakeryLoadingScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BakeryCream),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "☕",
                fontSize = 48.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Preparing your selection...",
                color = Espresso,
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Just a moment",
                color = MutedText,
                fontSize = 13.sp
            )
        }
    }
}


// =====================================================
// ERROR SCREEN
// =====================================================

@Composable
private fun BakeryErrorScreen(
    error: String,
    onBack: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BakeryCream)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Something went wrong",
                color = Espresso,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = error,
                color = MutedText,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBack,
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Espresso
                )
            ) {
                Text("Back to menu")
            }
        }
    }
}


// =====================================================
// DETAIL CONTENT
// =====================================================

@Composable
private fun DetailContent(
    item: MenuItem,
    onAddToCart: (MenuItem) -> Unit,
    onBack: () -> Unit
) {

    var quantity by remember { mutableIntStateOf(1) }

    val productEmoji = when (item.category_id.lowercase()) {
        "coffee" -> "☕"
        "bakery" -> "🥐"
        "dessert", "desserts" -> "🍰"
        else -> "☕"
    }

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
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // BACK BUTTON

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


            Text(
                text = "THE BREWKERY",
                color = Espresso,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                letterSpacing = 1.sp
            )


            // SPACER FOR BALANCE

            Spacer(
                modifier = Modifier.size(44.dp)
            )
        }


        // =====================================================
        // CONTENT
        // =====================================================

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {

            // PRODUCT HERO

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Espresso),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = productEmoji,
                    fontSize = 92.sp
                )

                if (item.badge.isNotEmpty()) {

                    Text(
                        text = item.badge.uppercase(),
                        color = Espresso,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(SoftCaramel)
                            .padding(
                                horizontal = 10.dp,
                                vertical = 6.dp
                            )
                    )
                }
            }


            Spacer(modifier = Modifier.height(22.dp))


            // PRODUCT NAME + PRICE

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = item.name,
                        color = Espresso,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = item.tagline,
                        color = MutedText,
                        fontSize = 14.sp
                    )
                }


                Text(
                    text = "₹${item.base_price.toInt()}",
                    color = Espresso,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(modifier = Modifier.height(16.dp))


            // DESCRIPTION

            Text(
                text = item.description,
                color = Mocha,
                fontSize = 14.sp,
                lineHeight = 21.sp
            )


            Spacer(modifier = Modifier.height(18.dp))


            HorizontalDivider(
                color = WarmBorder
            )


            Spacer(modifier = Modifier.height(16.dp))


            // PRODUCT INFO

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                DetailInfo(
                    title = "RATING",
                    value = "★ ${item.rating}"
                )

                DetailInfo(
                    title = "PREP TIME",
                    value = item.prep_time
                )

                DetailInfo(
                    title = "CALORIES",
                    value = "${item.calories} kcal"
                )
            }


            Spacer(modifier = Modifier.height(22.dp))


            // QUANTITY CARD

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
                        .padding(
                            horizontal = 18.dp,
                            vertical = 14.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "QUANTITY",
                            color = Caramel,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = "How many would you like?",
                            color = Espresso,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        QuantityButton(
                            text = "−",
                            enabled = quantity > 1,
                            onClick = {
                                if (quantity > 1) quantity--
                            }
                        )

                        Text(
                            text = quantity.toString(),
                            color = Espresso,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 18.dp)
                        )

                        QuantityButton(
                            text = "+",
                            enabled = true,
                            onClick = {
                                quantity++
                            }
                        )
                    }
                }
            }
        }


        // =====================================================
        // BOTTOM ADD TO CART BUTTON
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
                    top = 16.dp,
                    bottom = 20.dp
                )
            ) {

                Text(
                    text = "$quantity item${if (quantity > 1) "s" else ""} selected",
                    color = MutedText,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        onAddToCart(item)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Espresso
                    )
                ) {

                    Text(
                        text = "Add to cart   •   ₹${(item.base_price * quantity).toInt()}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


// =====================================================
// DETAIL INFO
// =====================================================

@Composable
private fun DetailInfo(
    title: String,
    value: String
) {

    Column {

        Text(
            text = title,
            color = Caramel,
            fontSize = 8.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            color = Espresso,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}


// =====================================================
// QUANTITY BUTTON
// =====================================================

@Composable
private fun QuantityButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {

    val background =
        if (enabled) Espresso else WarmBorder

    val textColor =
        if (enabled) Color.White else MutedText

    Box(
        modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}