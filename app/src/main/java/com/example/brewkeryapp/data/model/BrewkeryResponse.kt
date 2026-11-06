package com.example.brewkeryapp.data.model

data class BrewkeryResponse(
    val meta: Meta,
    val categories: List<Category>,
    val items: List<MenuItem>
)