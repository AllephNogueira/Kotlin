package com.allephnogueira.recuperandolista.api

import com.allephnogueira.recuperandolista.model.Fotos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FotosAPI {

    @GET("photos/{id}")
    suspend fun recuperarFoto(
        @Path("id") id: Int
    ) : Response<Fotos>

}