package com.example.matchresults.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


//TimeUtils содержит утилиты для работы с временем, включая конвертацию времени из UTC в локальное время.


object TimeUtils {

    @SuppressLint("ConstantLocale")
    private val utcFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

    @SuppressLint("ConstantLocale")
    private val localFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    fun convertUtcToLocal(utcTime: String): String {
        return try {
            val date = utcFormat.parse(utcTime)
            if (date != null) {
                localFormat.format(date)
            } else {
                utcTime // В случае ошибки возвращаем оригинальное значение
            }
        } catch (e: Exception) {
            e.printStackTrace()
            utcTime // В случае ошибки возвращаем оригинальное значение
        }
    }
}
