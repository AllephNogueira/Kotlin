package com.allephnogueira.viacep.api

import com.allephnogueira.viacep.model.Endereco
import retrofit2.Response
import retrofit2.http.GET

interface EnderecoAPI {

    @GET("ws/01001000/json/")
    suspend fun recuperarEndereco() : Response<Endereco>
}