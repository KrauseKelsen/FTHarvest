package com.pfflowers.ftharvest.api.services

import com.pfflowers.ftharvest.pojos.ApiResponse
import com.pfflowers.ftharvest.pojos.EncabezadoLectura
import com.pfflowers.ftharvest.pojos.LecturaCaja
import retrofit2.http.Body
import retrofit2.http.POST

interface ILecturaCajaService {

    @POST("v2/lecturacaja/Ingreso")
    suspend fun InsertarLecturas(@Body lecturas: List<LecturaCaja>): ApiResponse<LecturaCaja>

    @POST("v2/LecturaCaja")
    suspend fun getLecturas(@Body encabezadoLectura: EncabezadoLectura): ApiResponse<LecturaCaja>

}