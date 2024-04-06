package com.pfflowers.ftharvest.db.dao

import com.pfflowers.ftharvest.pojos.ApiResponse
import com.pfflowers.ftharvest.pojos.Planta
import retrofit2.Call
import retrofit2.http.GET

interface IPlantaService {
    @GET("v2/Planta")
    fun plantas(): Call<ApiResponse<Planta>>
}