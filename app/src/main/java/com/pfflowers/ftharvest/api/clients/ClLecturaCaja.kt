package com.pfflowers.ftharvest.api.clients

import com.pfflowers.ftharvest.api.services.ILecturaCajaService

object ClLecturaCaja {
    val service: ILecturaCajaService = ApiClient.buildService(ILecturaCajaService::class.java)


}

val ApiClient.sClLecturaCaja: ILecturaCajaService
    get() {
        return ClLecturaCaja.service
    }
