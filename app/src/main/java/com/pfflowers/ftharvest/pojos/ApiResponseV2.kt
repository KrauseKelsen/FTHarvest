package com.pfflowers.ftharvest.pojos

import com.google.gson.annotations.SerializedName

data class ApiResponseV2<T>(val Message: String = "", @SerializedName("Data")val data: List<T> = listOf())
