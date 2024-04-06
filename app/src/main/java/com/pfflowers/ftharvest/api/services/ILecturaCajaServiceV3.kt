package com.pfflowers.ftharvest.api.services

import com.pfflowers.ftharvest.pojos.LecturaCajaV2
import com.pfflowers.ftharvest.pojos.LecturaCajaV3
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.ArrayList

interface ILecturaCajaServiceV3 {
    @POST("api/NaveV2")
    fun post_lectura_caja(@Body jsonNaves: ArrayList<LecturaCajaV3>): Call<Void>
}
