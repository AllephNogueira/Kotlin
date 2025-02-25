package com.allephnogueira.recuperandolista.api

import com.allephnogueira.recuperandolista.model.Comentario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComentarioAPI {

    @GET("posts/{id}/comments")
    suspend fun recuperarComentariosParaPostagem(@Path("id") id: Int) : Response<List<Comentario>>


    @GET("comments")
    suspend fun recuperarComentariosParaPostagemQuery(@Query("postId") id: Int) : Response<List<Comentario>>


}