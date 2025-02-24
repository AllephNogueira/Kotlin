package com.allephnogueira.configuracaoretrofit.api

import com.allephnogueira.configuracaoretrofit.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {

    /* Aqui vamos passar somente as rotas, ja temos o BASE URL definido
     * https://viacep.com.br/ + ws/01001000/json/
     */
    @GET("ws/{cep}/json/")
    suspend fun recuperarEndereco(@Path("cep") cepDigitadoPeloUsuario: String) : Response<Endereco>
}