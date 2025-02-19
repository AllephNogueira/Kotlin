package com.allephnogueira.configuracaoretrofit.api

import com.allephnogueira.configuracaoretrofit.model.Endereco
import retrofit2.Response
import retrofit2.http.GET

interface EnderecoAPI {

    /* Aqui vamos passar somente as rotas, ja temos o BASE URL definido
     * https://viacep.com.br/ + ws/01001000/json/
     */
    @GET("ws/24465800/json/")
    suspend fun recuperarEndereco() : Response<Endereco>
}