package com.allephnogueira.projetoingresso.TesteAPI

import retrofit2.Call
import retrofit2.http.GET

interface IngressoApiService {
    @GET("v0/events/coming-soon/partnership/desafio")
    fun getUpcomingEvents(): Call<EventResponse>
}
