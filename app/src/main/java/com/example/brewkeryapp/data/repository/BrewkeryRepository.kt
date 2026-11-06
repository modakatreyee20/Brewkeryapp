package com.example.brewkeryapp.data.repository

import com.example.brewkeryapp.data.model.BrewkeryResponse
import com.example.brewkeryapp.data.model.MenuItem
import com.example.brewkeryapp.data.remote.BrewkeryApi
import com.example.brewkeryapp.data.remote.RetrofitClient

class BrewkeryRepository(
    private val api: BrewkeryApi = RetrofitClient.api
) {

    suspend fun getMenu(): BrewkeryResponse {
        return api.getMenu()
    }

    suspend fun getItemById(id: Int): MenuItem {
        return api.getItemById(id)
    }
}