package com.pfflowers.ftharvest.api.services

import com.pfflowers.ftharvest.pojos.LecturaCajaV2
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.ArrayList

interface ILecturaCajaServiceV2 {
    @POST("api/Nave")
    fun post_lectura_caja(@Body jsonNaves: ArrayList<LecturaCajaV2>): Call<Void>
}
