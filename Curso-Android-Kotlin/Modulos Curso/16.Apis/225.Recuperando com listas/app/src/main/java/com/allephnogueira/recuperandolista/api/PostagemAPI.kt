package com.allephnogueira.recuperandolista.api


import com.allephnogueira.recuperandolista.model.Postagem
import retrofit2.Response
import retrofit2.http.GET

interface PostagemAPI {

    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem>>
}
