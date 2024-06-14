package com.example.matchresults.data.api

import com.example.matchresults.model.MatchList
import retrofit2.http.GET

interface Api {
    @GET("/feed/json/epl-2023")
    suspend fun getMatches(): MatchList
}

// Api представляет интерфейс для выполнения запросов к серверу.