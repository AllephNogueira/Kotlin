package com.allephnogueira.viacep.api

import com.allephnogueira.viacep.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {

    @GET("ws/{cep}/json/")
    suspend fun recuperarEndereco(@Path("cep") cep: String) : Response<Endereco>
}