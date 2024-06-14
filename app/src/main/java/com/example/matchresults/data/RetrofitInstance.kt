package com.example.matchresults.data

import com.example.matchresults.data.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://fixturedownload.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    var api: Api? = null
        get() {
            if (field == null) {
                field = retrofit.create(Api::class.java)
            }
            return field
        }
}


//RetrofitInstance создает объект Retrofit для выполнения запросов через Api.


