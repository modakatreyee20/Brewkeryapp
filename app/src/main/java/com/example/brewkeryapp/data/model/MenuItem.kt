package com.example.brewkeryapp.data.model

data class MenuItem(
    val id: Int,
    val category_id: String,
    val name: String,
    val tagline: String,
    val description: String,
    val base_price: Double,
    val rating: Double,
    val review_count: Int,
    val prep_time: String,
    val calories: Int,
    val image_url: String,
    val badge: String,
    val ingredients: List<String>,
    val customizations: Customizations
)