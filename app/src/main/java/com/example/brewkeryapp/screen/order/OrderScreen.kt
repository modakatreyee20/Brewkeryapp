
package com.example.brewkeryapp.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// =====================================================
// CLASSIC BREWKERY COLORS
// =====================================================

private val BakeryCream = Color(0xFFF7F1E8)
private val SoftCream = Color(0xFFFFFCF7)
private val Espresso = Color(0xFF3B241B)
private val Mocha = Color(0xFF684334)
private val Caramel = Color(0xFFC58B5C)
private val SoftCaramel = Color(0xFFEBD7C2)
private val MutedText = Color(0xFF806B5E)


@Composable
fun OrderScreen(
    onContinueShopping: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BakeryCream),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // =====================================================
            // SUCCESS ICON
            // =====================================================

            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Espresso),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "✓",
                    color = Color.White,
                    fontSize = 46.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(modifier = Modifier.height(28.dp))


            Text(
                text = "Order confirmed!",
                color = Espresso,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )


            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = "Your favourites are being prepared with care.",
                color = MutedText,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )


            Spacer(modifier = Modifier.height(28.dp))


            // =====================================================
            // CONFIRMATION CARD
            // =====================================================

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SoftCream
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "THE BREWKERY",
                        color = Espresso,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "ORDER RECEIVED",
                        color = Caramel,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(SoftCaramel)
                    )


                    Spacer(modifier = Modifier.height(18.dp))


                    Text(
                        text = "Thank you for choosing Brewkery.",
                        color = Mocha,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Good coffee takes time. Great moments are worth the wait.",
                        color = MutedText,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }


            Spacer(modifier = Modifier.height(28.dp))


            // =====================================================
            // PREPARING CARD
            // =====================================================

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SoftCaramel
                )
            ) {

                Column(
                    modifier = Modifier.padding(
                        horizontal = 18.dp,
                        vertical = 16.dp
                    )
                ) {

                    Text(
                        text = "☕  NOW BREWING",
                        color = Espresso,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "We'll prepare your order fresh and with care.",
                        color = Mocha,
                        fontSize = 13.sp
                    )
                }
            }


            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = onContinueShopping,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Espresso
                )
            ) {

                Text(
                    text = "Continue exploring",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(modifier = Modifier.height(14.dp))


            Text(
                text = "Made fresh. Served with love.",
                color = Caramel,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
