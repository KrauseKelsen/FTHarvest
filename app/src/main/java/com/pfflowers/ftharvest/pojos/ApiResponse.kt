package com.pfflowers.ftharvest.pojos

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(val Message: String = "", @SerializedName("Data")val data: List<T> = listOf())
