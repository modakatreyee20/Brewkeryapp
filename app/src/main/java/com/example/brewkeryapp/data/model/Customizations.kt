package com.example.brewkeryapp.data.model

data class Customizations(
    val sizes: List<SizeOption>,
    val sugar_levels: List<String>,
    val milk_options: List<MilkOption>
)