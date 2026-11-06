package com.example.brewkeryapp.data.api

import com.example.brewkeryapp.data.model.BrewkeryResponse
import com.example.brewkeryapp.data.model.MenuItem
import retrofit2.http.GET
import retrofit2.http.Path

interface BrewkeryApi {

    @GET("data.json")
    suspend fun getMenu(): BrewkeryResponse

    @GET("api/items/{id}.json")
    suspend fun getItem(
        @Path("id") id: Int
    ): MenuItem
}