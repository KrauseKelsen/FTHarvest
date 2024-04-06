package com.pfflowers.ftharvest.api.clients

import com.pfflowers.ftharvest.api.services.ILecturaCajaServiceV2

object ClLecturaCajaV2 {
    val service: ILecturaCajaServiceV2 = ApiClientV2.buildService(ILecturaCajaServiceV2::class.java)


}

val ApiClientV2.sClLecturaCaja: ILecturaCajaServiceV2
    get() {
        return ClLecturaCajaV2.service
    }
