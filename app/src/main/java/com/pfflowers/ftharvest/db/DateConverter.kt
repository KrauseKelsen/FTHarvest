package com.pfflowers.ftharvest.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class DateConverter {
//    var df: DateFormat = SimpleDateFormat("dd/M/yyyy", Locale("spanish"))
//    @TypeConverter
//    fun fromTimestamp(value: String?): Date? {
//
//        return value?.let { df.parse(it) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: Date?): String? {
//        return date?.let {
//            df.format(it)
//        }
//    }

    var df: DateFormat = SimpleDateFormat("dd/M/yyyy", Locale("spanish"))
    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        var date = Date.from(Instant.now())
        try {
            date = value?.let { df.parse(it) }!!
        }catch (e: Exception){
            e.printStackTrace()
        }
        return date
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): String {
        var value = Date.from(Instant.now()).toString()
        try {
            value = date?.let {
                df.format(it)
            }.toString()
        }catch (e: Exception){
            e.printStackTrace()
        }
        return value
    }
}
