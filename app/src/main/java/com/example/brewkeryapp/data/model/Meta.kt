package com.example.brewkeryapp.data.model

data class Meta(
    val app: String,
    val version: String,
    val tagline: String,
    val currency: String,
    val currency_symbol: String,
    val delivery_fee: Double,
    val tax_rate_percent: Double,
    val estimated_delivery_time: String
)